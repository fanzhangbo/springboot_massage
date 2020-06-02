package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Account;
import com.fzb.massage.entity.Massage;
import com.fzb.massage.entity.MassageOrder;
import com.fzb.massage.mapper.MassageOrderMapper;
import com.fzb.massage.service.AccountService;
import com.fzb.massage.service.MassageOrderService;
import com.fzb.massage.service.MassageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Service
public class MassageOrderServiceImpl implements MassageOrderService {

    @Autowired
    public MassageOrderMapper massageOrderMapper;

    @Autowired
    public AccountService accountService;

    @Autowired
    public MassageService massageService;

    @Override
    public List<MassageOrder> listMassageOrder(Map condition) {
        return  this.massageOrderMapper.listMassageOrder(condition);

    }

    @Override
    public List<MassageOrder> listTotalMassageOrder(Map condition) {
        return this.massageOrderMapper.listTotalMassageOrder(condition);
    }

    @Override
    public MassageOrder findMassageOrderById(Long id) {
        if (id == null) {
            return null;
        }
        return this.massageOrderMapper.findMassageOrderById(id);
    }

    @Override
    public void addMassageOrder(MassageOrder massageOrder) {
        massageOrder.setCreateTime(new Date());
        this.massageOrderMapper.addMassageOrder(massageOrder);
    }

    @Transactional
    @Override
    public void updateMassageOrder(MassageOrder massageOrder) {
        this.massageOrderMapper.updateMassageOrder(massageOrder);

        if(massageOrder.getStatus() == 1 || massageOrder.getStatus() == 0) {
            // 更新账户
            MassageOrder massageOrderById = this.massageOrderMapper.findMassageOrderById(massageOrder.getId());
            Massage massageById = this.massageService.findMassageById(massageOrderById.getMassageId());

            Account account = new Account();
            if (massageOrder.getStatus() == 1) {
                // 消费
                account.setType(1);
                account.setRemark("消费" + massageById.getName());
            }  else {
                // 撤销
                account.setType(2);
                account.setRemark("撤销" + massageById.getName());
            }
            account.setCreateTime(new Date());
            account.setMoney(massageById.getPrice());
            account.setUserId(massageOrderById.getUserId());

            this.accountService.addAccount(account);
        }

    }

    @Override
    public void delMassageOrder(Long id) {
        this.massageOrderMapper.delMassageOrder(id);
    }
}
