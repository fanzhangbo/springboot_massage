package com.fzb.massage.controller;

import com.fzb.massage.entity.Worker;
import com.fzb.massage.service.WorkerService;
import com.fzb.massage.utils.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@RestController
@RequestMapping("api/worker")
public class ApiWorkerController {

    @Autowired
    private WorkerService workerService;

    @GetMapping("listWorker")
    public GlobalResult listWorker() {

        List<Worker> workers = this.workerService.listWorker(null);
        return GlobalResult.build(1, "技师列表", workers);
    }

    @GetMapping("detail")
    public GlobalResult detail(Long id) {
        Worker workerById = this.workerService.findWorkerById(id);
        return GlobalResult.build(1, "技师详情", workerById);
    }

}
