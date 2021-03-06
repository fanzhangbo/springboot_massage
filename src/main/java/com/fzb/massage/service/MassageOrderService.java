package com.fzb.massage.service;

import com.fzb.massage.entity.MassageOrder;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface MassageOrderService {
    /**
     * 列表
     * @return
     */
    List<MassageOrder> listMassageOrder(Map condition);

    List<MassageOrder> listTotalMassageOrder(Map condition);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    MassageOrder findMassageOrderById(Long id);

    /**
     * 添加
     */
    void addMassageOrder(MassageOrder order);

    /**
     * 根据id更新
     * @param massageOrder
     */
    void updateMassageOrder(MassageOrder massageOrder);

    /**
     * 删除
     */
    void delMassageOrder(Long id);
}
