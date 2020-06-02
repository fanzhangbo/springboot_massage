package com.fzb.massage.service.impl;

import com.fzb.massage.entity.User;
import com.fzb.massage.mapper.UserMapper;
import com.fzb.massage.service.UserService;
import com.fzb.massage.utils.encrypt.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userMapper;

    @Override
    public List<User> listUser(Map condition) {
        return this.userMapper.listUser(condition);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        User userByUsername = this.userMapper.findUserByUsername(username);
        if (null != userByUsername) {
            if (userByUsername.getPassword().equals(PasswordUtil.encrypt(password))) {
                return userByUsername;
            }
        }
        return null;
    }

    @Override
    public User findUserById(Long id) {
        if (id == null) {
            return null;
        }
        return this.userMapper.findUserById(id);
    }

    @Override
    public void addUser(User user) {
        user.setCreateTime(new Date());
        if(user.getPassword() != null) {
            user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        }
        this.userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        if(user.getPassword() != null) {
            user.setPassword(PasswordUtil.encrypt(user.getPassword()));
        }
        this.userMapper.updateUser(user);
    }

    @Override
    public void delUser(Long id) {
        this.userMapper.delUser(id);
    }
}
