package com.fzb.massage.service;

import com.fzb.massage.entity.Account;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface AccountService {
    /**
     * 列表
     * @return
     */
    List<Account> listAccount(Map condition);


    /**
     * 根据用户名查找用户
     * @param userId
     * @return
     */
    Account findUserByUserId(Long userId);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Account findAccountById(Long id);

    /**
     * 添加
     */
    void addAccount(Account account);

    /**
     * 根据id更新
     * @param account
     */
    void updateAccount(Account account);

    /**
     * 删除
     */
    void delAccount(Long id);
}
