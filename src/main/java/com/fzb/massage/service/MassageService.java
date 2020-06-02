package com.fzb.massage.service;

import com.fzb.massage.entity.Massage;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
public interface MassageService {

    /**
     * 列表
     * @return
     */
    List<Massage> listMassage(Map condition);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Massage findMassageById(Long id);

    /**
     * 添加
     */
    void addMassage(Massage massage);

    /**
     * 根据id更新
     * @param massage
     */
    void updateMassage(Massage massage);

    /**
     * 删除
     */
    void delMassage(Long id);

}
