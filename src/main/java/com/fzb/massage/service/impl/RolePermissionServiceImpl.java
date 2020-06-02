package com.fzb.massage.service.impl;

import com.fzb.massage.entity.AdminRole;
import com.fzb.massage.entity.RolePermission;
import com.fzb.massage.mapper.AdminRoleMapper;
import com.fzb.massage.mapper.RolePermissionMapper;
import com.fzb.massage.service.AdminRoleService;
import com.fzb.massage.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    public void removeByRoleId(Long roleId) {
        this.rolePermissionMapper.removeByRoleId(roleId);
    }

    public void insertList(List<RolePermission> rolePermissions){
        this.rolePermissionMapper.insertList(rolePermissions);
    }

    @Override
    public List<RolePermission> listRolePermissionByRoleId(Long roldId) {
        return this.rolePermissionMapper.listRolePermissionByRoleId(roldId);
    }


    @Override
    public void addRolePermission(Long roleId, String permissionIds) {
        //删除
        removeByRoleId(roleId);
        //添加
        String[] permissionArr = permissionIds.split(",");
        if (permissionArr.length == 0) {
            return;
        }
        RolePermission u = null;
        List<RolePermission> rolePermissions = new ArrayList<>();
        for (String permissionId : permissionArr) {
            if (StringUtils.isEmpty(permissionId)) {
                continue;
            }
            u = new RolePermission();
            u.setRoleId(roleId);
            u.setPermissionId(Long.parseLong(permissionId));
            rolePermissions.add(u);
        }
        insertList(rolePermissions);
    }
}
