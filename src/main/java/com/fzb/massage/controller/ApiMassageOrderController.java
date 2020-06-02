package com.fzb.massage.controller;

import com.fzb.massage.entity.MassageOrder;
import com.fzb.massage.service.MassageOrderService;
import com.fzb.massage.utils.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@RestController
@RequestMapping("api/massageOrder")
public class ApiMassageOrderController {

    @Autowired
    private MassageOrderService massageOrderService;

    @GetMapping("listMassageOrder")
    public GlobalResult listMassageOrder(Long userId) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("userId", userId);
        List<MassageOrder> massageOrders = this.massageOrderService.listTotalMassageOrder(condition);
        return GlobalResult.build(1, "预约列表", massageOrders);
    }

    @GetMapping("listByWorkerIdAndOrderTime")
    public GlobalResult listMassageOrderByWorkerId(Long workerId, String orderTime) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("workerId", workerId);
        condition.put("orderTime", orderTime);
        List<MassageOrder> massageOrders = this.massageOrderService.listTotalMassageOrder(condition);
        return GlobalResult.build(1, "根据技师获取预约列表", massageOrders);
    }

    @GetMapping("detail")
    public GlobalResult detail(Long id) {
        MassageOrder massageOrderById = this.massageOrderService.findMassageOrderById(id);
        return GlobalResult.build(1, "预约详情", massageOrderById);
    }

    @RequestMapping(value = "addMassageOrder", method = RequestMethod.POST)
    public GlobalResult addMassageOrder(MassageOrder massageOrder) {
        if (null != massageOrder) {
            this.massageOrderService.addMassageOrder(massageOrder);
            return GlobalResult.build(1, "预约成功");
        }
        return GlobalResult.build(0, "预约失败");
    }

}
