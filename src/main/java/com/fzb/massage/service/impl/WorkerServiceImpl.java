package com.fzb.massage.service.impl;

import com.fzb.massage.entity.Worker;
import com.fzb.massage.mapper.WorkerMapper;
import com.fzb.massage.service.WorkerService;
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
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    public WorkerMapper workerMapper;

    @Override
    public List<Worker> listWorker(Map condition) {
        return this.workerMapper.listWorker(condition);
    }

    @Override
    public Worker findWorkerById(Long id) {
        if (id == null) {
            return null;
        }
        return this.workerMapper.findWorkerById(id);
    }

    @Override
    public void addWorker(Worker worker) {
        worker.setCreateTime(new Date());
        this.workerMapper.addWorker(worker);
    }

    @Override
    public void updateWorker(Worker worker) {
        this.workerMapper.updateWorker(worker);
    }

    @Override
    public void delWorker(Long id) {
        this.workerMapper.delWorker(id);
    }
}
