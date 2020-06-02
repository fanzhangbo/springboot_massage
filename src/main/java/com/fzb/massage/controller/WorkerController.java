package com.fzb.massage.controller;

import com.fzb.massage.entity.Worker;
import com.fzb.massage.service.WorkerService;
import com.fzb.massage.utils.GlobalResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Controller
@RequestMapping("worker")
public class WorkerController {

    @Autowired
    public WorkerService workerService;

    @RequestMapping("index")
    public String index(Model model, Integer pageNum,
                        @RequestParam(required = false) String search_field,
                        @RequestParam(required = false) String kw) {

        if (null == pageNum) {
            pageNum = 1;
        }
        Page<Worker> pageWorker = PageHelper.startPage(pageNum, 6);
        Map<String, Object>  condition = new HashMap<>();
        if (null != kw && !("".equals(kw))) {
            if ("name".equals(search_field)) {
                condition.put("name", kw);
            }
        }
        List<Worker> workers = this.workerService.listWorker(condition);
        long total = pageWorker.getTotal();

        model.addAttribute("pageTotal", total);
        model.addAttribute("workers", workers);
        return "worker/index";
    }

    @RequestMapping("detail")
    @ResponseBody
    public GlobalResult detail(Long id) {
        try {
            Worker workerById = this.workerService.findWorkerById(id);
            if (workerById != null) {
                return GlobalResult.build(1, "workerDetail", workerById);
            }
            return GlobalResult.build(0, "该用户不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("getPage")
    @ResponseBody
    public GlobalResult getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Worker> workers = this.workerService.listWorker(null);

        return GlobalResult.build(1, "success", workers);
    }

    @RequestMapping(value = "addWorker", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult addWorker(Worker worker) {
        try {
            this.workerService.addWorker(worker);
            return GlobalResult.build(1, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "添加失败");
        }
    }

    @RequestMapping(value = "editWorker", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult editWorker(Worker worker) {
        try {
            this.workerService.updateWorker(worker);
            return GlobalResult.build(1, "编辑成功");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "编辑失败");
        }
    }



    @RequestMapping(value = "delWorker")
    @ResponseBody
    public GlobalResult addWorker(Long id) {
        try {
            this.workerService.delWorker(id);
            return GlobalResult.build(1, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "删除失败");
        }
    }


}
