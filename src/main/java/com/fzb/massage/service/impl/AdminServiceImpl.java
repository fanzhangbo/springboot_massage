package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Admin;
import com.fzb.massage.mapper.AdminMapper;
import com.fzb.massage.service.AdminService;
import com.fzb.massage.utils.encrypt.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> listAdmin() {
        return this.adminMapper.listAdmin();
    }

    @Override
    public List<Admin> findAdminRole() {
        return this.adminMapper.findAdminRole();
    }

    @Override
    public Admin findAdminByName(String name) {
        return this.adminMapper.findAdminByName(name);
    }

    @Override
    public Admin findAdminById(Long id) {
        return this.adminMapper.findAdminById(id);
    }

    @Override
    public void addAdmin(Admin admin) {
        if (StringUtils.isEmpty(admin.getPassword())) {
            throw new RuntimeException("密码不能为空");
        }
        admin.setPassword(PasswordUtil.encrypt(admin.getPassword()));
        this.adminMapper.addAdmin(admin);
    }

    @Override
    public void updateAdmin(Admin admin) {
        if (StringUtils.isEmpty(admin.getPassword())) {
            admin.setPassword(null);
        }
        this.adminMapper.updateAdmin(admin);
    }

    @Override
    public void delAdmin(Long id) {
        this.adminMapper.delAdmin(id);
    }
}
