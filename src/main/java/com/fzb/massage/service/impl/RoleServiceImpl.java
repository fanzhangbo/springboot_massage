package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Role;
import com.fzb.massage.mapper.RoleMapper;
import com.fzb.massage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> listRole() {
        return this.roleMapper.listRole();
    }

    @Override
    public List<Role> listRoleByAdminId(Long adminId) {
        return this.roleMapper.listRoleByAdminId(adminId);
    }

    @Override
    public Role findRoleById(Long id) {
        return this.roleMapper.findRoleById(id);
    }

    @Override
    public void addRole(Role role) {
        this.roleMapper.addRole(role);
    }

    @Override
    public void updateRole(Role role) {
        this.roleMapper.updateRole(role);
    }

    @Override
    public void delRole(Long id) {
        this.roleMapper.delRole(id);
    }
}
