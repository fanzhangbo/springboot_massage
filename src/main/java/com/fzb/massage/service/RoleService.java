package com.fzb.massage.service;

import com.fzb.massage.entity.Role;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface RoleService {

    List<Role> listRole();


    List<Role> listRoleByAdminId(Long adminId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role findRoleById(Long id);

    /**
     * 添加
     */
    void addRole(Role role);

    /**
     * 根据id更新
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除
     */
    void delRole(Long id);


}
