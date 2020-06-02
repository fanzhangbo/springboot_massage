package com.fzb.massage.service;

import com.fzb.massage.entity.Permission;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface PermissionService {

    public List<Permission> listPermission(Boolean isTree);

    /**
     * 查询权限和地址
     * @return
     */
    List<Permission> listPermsAndUrl();

    /**
     * 根据adminId查询权限
     * @param adminId
     * @return
     */
    List<Permission> listPermissionByAdminId(Long adminId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Permission findPermissionById(Long id);

    /**
     * 添加
     */
    void addPermission(Permission permission);

    /**
     * 根据id更新
     * @param permission
     */
    void updatePermission(Permission permission);

    /**
     * 删除
     */
    void delPermission(Long id);


}
