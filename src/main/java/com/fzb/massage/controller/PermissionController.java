package com.fzb.massage.controller;

import com.fzb.massage.entity.Permission;
import com.fzb.massage.entity.Role;
import com.fzb.massage.entity.User;
import com.fzb.massage.service.PermissionService;
import com.fzb.massage.service.RoleService;
import com.fzb.massage.utils.GlobalResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Controller
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("index")
    public String index(Model model) {

        List<Permission> permissions = this.permissionService.listPermission(true);
        model.addAttribute("permissions", permissions);
        return "permission/index";
    }

    @RequestMapping("detail")
    @ResponseBody
    public GlobalResult detail(Long id) {
        try {
            Permission permission = this.permissionService.findPermissionById(id);
            if (permission != null) {
                return GlobalResult.build(1, "permissionDetail", permission);
            }
            return GlobalResult.build(0, "不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("addPermission")
    @ResponseBody
    public GlobalResult addPermission(Permission permission) {
        try {
            this.permissionService.addPermission(permission);
            if (permission != null) {
                return GlobalResult.build(1, "添加成功");
            }
            return GlobalResult.build(0, "添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("editPermission")
    @ResponseBody
    public GlobalResult editPermission(Permission permission) {
        try {
            this.permissionService.updatePermission(permission);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("delPermission")
    @ResponseBody
    public GlobalResult delPermission(Long id) {
        try {
            this.permissionService.delPermission(id);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }


}
