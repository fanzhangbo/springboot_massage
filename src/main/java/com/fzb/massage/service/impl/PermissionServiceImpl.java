package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Permission;
import com.fzb.massage.mapper.PermissionMapper;
import com.fzb.massage.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> listPermission(Boolean isTree) {
        if (isTree) {
            return tree(this.permissionMapper.listPermission(), 0L);
        } else {
            return this.permissionMapper.listPermission();
        }

    }

    @Override
    public List<Permission> listPermsAndUrl() {
        return this.permissionMapper.listPermsAndUrl();
    }

    @Override
    public List<Permission> listPermissionByAdminId(Long adminId) {
        return this.permissionMapper.listPermissionByAdminId(adminId);
    }

    @Override
    public Permission findPermissionById(Long id) {
        return this.permissionMapper.findPermissionById(id);
    }

    @Override
    public void addPermission(Permission permission) {
        this.permissionMapper.addPermission(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        this.permissionMapper.updatePermission(permission);
    }

    @Override
    public void delPermission(Long id) {
        this.permissionMapper.delPermission(id);
    }


    // 获取树形菜单列表
    private static List<Permission> tree(List<Permission> perms, Long pid) {

        List<Permission> treePermissions = new LinkedList<>();
        // 获取一级级菜单
        for (Permission perm : perms) {
            if (perm.getPid().equals(pid)) {
                treePermissions.add(perm);

                // 获取二级菜单
                for (Permission perm1 : perms) {
                    if (!perm.equals(perm1) && perm1.getPid().equals(perm.getPermissionId())) {
                        perm.getChild().add(perm1);
                    }
                }
            }
        }
        return treePermissions;

    }

}
