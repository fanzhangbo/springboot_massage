package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Account;
import com.fzb.massage.entity.User;
import com.fzb.massage.mapper.AccountMapper;
import com.fzb.massage.mapper.UserMapper;
import com.fzb.massage.service.AccountService;
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
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Account> listAccount(Map condition) {
        return this.accountMapper.listAccount(condition);
    }

    @Override
    public Account findUserByUserId(Long userId) {
        return this.accountMapper.findUserByUserId(userId);
    }

    @Override
    public Account findAccountById(Long id) {
        return this.accountMapper.findAccountById(id);
    }

    @Override
    public void addAccount(Account account) {
        account.setCreateTime(new Date());
        this.accountMapper.addAccount(account);
        if(account.getType() != null && account.getUserId() != null) {
            User userById = this.userMapper.findUserById(account.getUserId());
            // 1 消费  2充值   更新用户余额
            if (userById != null) {
                if (account.getType() == 1) {
                    userById.setMoney(userById.getMoney().subtract(account.getMoney()));
                    this.userMapper.updateUser(userById);
                } else if (account.getType() == 2) {
                    userById.setMoney(userById.getMoney().add(account.getMoney()));
                    this.userMapper.updateUser(userById);
                }
            }
        }
    }

    @Override
    public void updateAccount(Account account) {
        this.accountMapper.updateAccount(account);
    }

    @Override
    public void delAccount(Long id) {
        this.accountMapper.delAccount(id);
    }
}
