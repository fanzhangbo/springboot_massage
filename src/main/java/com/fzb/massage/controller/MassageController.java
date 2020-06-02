package com.fzb.massage.controller;

import com.fzb.massage.entity.Massage;
import com.fzb.massage.service.MassageService;
import com.fzb.massage.utils.FileUtil;
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
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Controller
@RequestMapping("massage")
public class MassageController {

    @Autowired
    public MassageService massageService;

    @RequestMapping("index")
    public String index(Model model, Integer pageNum,
                        @RequestParam(required = false) String kw) {

        if (null == pageNum) {
            pageNum = 1;
        }
        Page<Massage> pageMassage = PageHelper.startPage(pageNum, 6);
        Map<String, Object>  condition = new HashMap<>();
        if (null != kw && !("".equals(kw))) {
            condition.put("name", kw);
        }
        List<Massage> massages = this.massageService.listMassage(condition);
        long total = pageMassage.getTotal();

        model.addAttribute("pageTotal", total);
        model.addAttribute("massages", massages);
        return "massage/index";
    }

    @RequestMapping("detail")
    @ResponseBody
    public GlobalResult detail(Long id) {
        try {
            Massage massageById = this.massageService.findMassageById(id);
            if (massageById != null) {
                return GlobalResult.build(1, "massageDetail", massageById);
            }
            return GlobalResult.build(0, "该服务不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("getPage")
    @ResponseBody
    public GlobalResult getPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Massage> massages = this.massageService.listMassage(null);

        return GlobalResult.build(1, "success", massages);
    }

    @RequestMapping(value = "addMassage", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult addMassage(Massage massage) {
        try {

            System.out.println(massage);

            this.massageService.addMassage(massage);
            return GlobalResult.build(1, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "添加失败");
        }
    }

    @RequestMapping(value = "uploadImg", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult uploadImg(MultipartFile img) {
        try {
            return new FileUtil().uploadFile(img);

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "上传失败");
        }
    }



    @RequestMapping(value = "editMassage", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult editMassage(Massage massage) {
        try {
            this.massageService.updateMassage(massage);
            return GlobalResult.build(1, "编辑成功");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "编辑失败");
        }
    }



    @RequestMapping(value = "delMassage")
    @ResponseBody
    public GlobalResult addMassage(Long id) {
        try {
            this.massageService.delMassage(id);
            return GlobalResult.build(1, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "删除失败");
        }
    }


}
