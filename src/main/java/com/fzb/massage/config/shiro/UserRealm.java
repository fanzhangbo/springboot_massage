package com.fzb.massage.config.shiro;

import com.fzb.massage.entity.Admin;
import com.fzb.massage.entity.Permission;
import com.fzb.massage.entity.Role;
import com.fzb.massage.service.AdminService;
import com.fzb.massage.service.PermissionService;
import com.fzb.massage.service.RoleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * TODO
 *  自定义Realm
 * @author Fan Zhangbo
 */

public class UserRealm  extends AuthorizingRealm {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权开始");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        Admin currentAdmin = (Admin) subject.getPrincipal();


        // 赋予角色


        //info.addStringPermission("massage:index");
        // 拿到当前登录的这个用户

        List<Permission> permissions = null;
        List<Role> roleList = null;
        if (currentAdmin.getAdminId() == 1L) {
            permissions = this.permissionService.listPermission(false);
            roleList = this.roleService.listRole();
        } else {
            // 查询权限
            permissions = this.permissionService.listPermissionByAdminId(currentAdmin.getAdminId());
            roleList = this.roleService.listRoleByAdminId(currentAdmin.getAdminId());
        }
        for (Role role : roleList) {
            info.addRole(role.getRoleCode());
        }


        if (!CollectionUtils.isEmpty(permissions)) {
            for (Permission permission : permissions) {
                if (!StringUtils.isEmpty(permission.getPerms())) {
                    info.addStringPermission(permission.getPerms());
                }

            }
        }

        return info;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证开始");

        // 用户名  密码  数据库中取得
//        String username = "root";
//        String password = "123456";



        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;

        // 数据库中查
        Admin adminByName = this.adminService.findAdminByName(userToken.getUsername());

        if (adminByName == null) {
            return null;        // 抛出异常
        }
        // 设置session
        SecurityUtils.getSubject().getSession().setAttribute("loginAdmin", adminByName);

        // 密码认证shiro做了

        return new SimpleAuthenticationInfo(adminByName, adminByName.getPassword(), "");
    }
}
