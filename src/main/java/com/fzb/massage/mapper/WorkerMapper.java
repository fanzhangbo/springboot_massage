package com.fzb.massage.mapper;

import com.fzb.massage.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Mapper
public interface WorkerMapper {

    /**
     * 列表
     * @return
     */
    List<Worker> listWorker(Map condition);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Worker findWorkerById(Long id);

    /**
     * 添加
     */
    void addWorker(Worker worker);

    /**
     * 根据id更新
     * @param worker
     */
    void updateWorker(Worker worker);

    /**
     * 删除
     */
    void delWorker(Long id);
}
