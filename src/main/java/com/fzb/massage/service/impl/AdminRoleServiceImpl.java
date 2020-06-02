package com.fzb.massage.service.impl;

import com.fzb.massage.entity.AdminRole;
import com.fzb.massage.mapper.AdminRoleMapper;
import com.fzb.massage.service.AdminRoleService;
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
public class AdminRoleServiceImpl implements AdminRoleService {

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    public void removeByAdminId(Long adminId) {
        this.adminRoleMapper.removeByAdminId(adminId);
    }

    public void insertList(List<AdminRole> adminRoles){
        this.adminRoleMapper.insertList(adminRoles);
    }

    @Override
    public List<AdminRole> listAdminRoleByAdminId(Long adminId) {
        return this.adminRoleMapper.listAdminRoleByAdminId(adminId);
    }


    @Override
    public void addAdminRole(Long adminId, String roleIds) {
        //删除
        removeByAdminId(adminId);
        //添加
        String[] roleIdArr = roleIds.split(",");
        if (roleIdArr.length == 0) {
            return;
        }
        AdminRole u = null;
        List<AdminRole> roles = new ArrayList<>();
        for (String roleId : roleIdArr) {
            if (StringUtils.isEmpty(roleId)) {
                continue;
            }
            u = new AdminRole();
            u.setAdminId(adminId);
            u.setRoleId(Long.parseLong(roleId));
            roles.add(u);
        }
        insertList(roles);
    }
}
