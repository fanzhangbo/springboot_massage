package com.fzb.massage.mapper;

import com.fzb.massage.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Mapper
public interface PermissionMapper {


    List<Permission> listPermission();

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
