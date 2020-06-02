package com.fzb.massage.service;

import com.fzb.massage.entity.User;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface UserService {
    /**
     * 列表
     * @return
     */
    List<User> listUser(Map condition);

    /**
     * 根据用户名查找用户
     * @param username
     * @return
     */
    User findUserByUsernameAndPassword(String username, String password);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findUserById(Long id);

    /**
     * 添加
     */
    void addUser(User user);

    /**
     * 根据id更新
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除
     */
    void delUser(Long id);
}
