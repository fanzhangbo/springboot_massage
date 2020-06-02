package com.fzb.massage.controller;

import com.fzb.massage.entity.Admin;
import com.fzb.massage.entity.AdminRole;
import com.fzb.massage.entity.Role;
import com.fzb.massage.entity.User;
import com.fzb.massage.service.AdminRoleService;
import com.fzb.massage.service.AdminService;
import com.fzb.massage.service.RoleService;
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
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AdminRoleService adminRoleService;

    @RequestMapping("index")
    public String index(Model model, Integer pageNum,
                        @RequestParam(required = false) String search_field,
                        @RequestParam(required = false) String kw) {

        if (null == pageNum) {
            pageNum = 1;
        }
        Page<User> pageUser = PageHelper.startPage(pageNum, 6);
//        Map<String, Object> condition = new HashMap<>();
//
//        if (null != kw && !("".equals(kw))) {
//            if ("name".equals(search_field)) {
//                condition.put("username", kw);
//            }
//        }
        List<Admin> admins = this.adminService.listAdmin();
        long total = pageUser.getTotal();

        model.addAttribute("pageTotal", total);
        model.addAttribute("admins", admins);
        return "admin/index";
    }

    @RequestMapping("detail")
    @ResponseBody
    public GlobalResult detail(Long id) {
        try {
            Admin admin = this.adminService.findAdminById(id);
            if (admin != null) {
                return GlobalResult.build(1, "adminDetail", admin);
            }
            return GlobalResult.build(0, "不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("chooseRoles")
    @ResponseBody
    public GlobalResult chooseRoles(Long id) {
        List<Role> roles = this.roleService.listRole();
        List<AdminRole> adminRoles = this.adminRoleService.listAdminRoleByAdminId(id);
        Map<String, List<?>> retMap = new HashMap<>();
        retMap.put("roles", roles);
        retMap.put("adminRoles", adminRoles);
        return GlobalResult.build(1, "角色列表", retMap);
    }

    @RequestMapping(value = "chooseRoles" , method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult chooseRoles(Long adminId, String roleStr) {
        try {
            this.adminRoleService.addAdminRole(adminId, roleStr);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "操作失败");
        }
    }

    @RequestMapping("addAdmin")
    @ResponseBody
    public GlobalResult addAdmin(Admin admin) {
        try {
            this.adminService.addAdmin(admin);
            if (admin != null) {
                return GlobalResult.build(1, "添加成功");
            }
            return GlobalResult.build(0, "添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("editAdmin")
    @ResponseBody
    public GlobalResult editAdmin(Admin admin) {
        try {
            this.adminService.updateAdmin(admin);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("delAdmin")
    @ResponseBody
    public GlobalResult delAdmin(Long id) {
        try {
            this.adminService.delAdmin(id);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

}
