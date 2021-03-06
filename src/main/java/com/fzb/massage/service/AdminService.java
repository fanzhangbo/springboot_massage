package com.fzb.massage.service;

import com.fzb.massage.entity.Admin;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface AdminService {

    List<Admin> listAdmin();


    List<Admin> findAdminRole();


    Admin findAdminByName(String name);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Admin findAdminById(Long id);

    /**
     * 添加
     */
    void addAdmin(Admin admin);

    /**
     * 根据id更新
     * @param admin
     */
    void updateAdmin(Admin admin);

    /**
     * 删除
     */
    void delAdmin(Long id);


}
