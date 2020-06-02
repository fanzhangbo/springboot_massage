package com.fzb.massage.controller;

import com.fzb.massage.entity.Massage;
import com.fzb.massage.service.MassageService;
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
@RequestMapping("api/massage")
public class ApiMassageController {

    @Autowired
    private MassageService massageService;

    @GetMapping("listMassage")
    public GlobalResult listMassage() {

        List<Massage> massages = this.massageService.listMassage(null);
        return GlobalResult.build(1, "项目列表", massages);
    }

    @GetMapping("detail")
    public GlobalResult detail(Long id) {

        Massage massageById = this.massageService.findMassageById(id);
        return GlobalResult.build(1, "项目详情", massageById);
    }

}
