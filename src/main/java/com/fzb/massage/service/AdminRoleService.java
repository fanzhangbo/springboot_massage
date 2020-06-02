package com.fzb.massage.service;

import com.fzb.massage.entity.AdminRole;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface AdminRoleService {

    public void removeByAdminId(Long adminId);

    public void addAdminRole(Long userId, String roleIds);

    public void insertList(List<AdminRole> adminRoles);


    public List<AdminRole> listAdminRoleByAdminId(Long adminId);
}
