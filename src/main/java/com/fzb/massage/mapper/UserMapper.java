package com.fzb.massage.mapper;

import com.fzb.massage.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Mapper
public interface UserMapper {

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
    User findUserByUsername(String username);

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
