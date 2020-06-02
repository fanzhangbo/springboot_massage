package com.fzb.massage.service;

import com.fzb.massage.entity.AdminRole;
import com.fzb.massage.entity.RolePermission;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface RolePermissionService {

    public void removeByRoleId(Long roleId);

    public void addRolePermission(Long roleId, String permissionIds);

    public void insertList(List<RolePermission> rolePermissions);

    List<RolePermission> listRolePermissionByRoleId(Long roldId);
}
