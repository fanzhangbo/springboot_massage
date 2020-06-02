package com.fzb.massage.controller;

import com.fzb.massage.entity.*;
import com.fzb.massage.service.*;
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
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @RequestMapping("index")
    public String index(Model model, Integer pageNum,
                        @RequestParam(required = false) String search_field,
                        @RequestParam(required = false) String kw) {

        if (null == pageNum) {
            pageNum = 1;
        }
        Page<User> pageUser = PageHelper.startPage(pageNum, 6);
        List<Role> roles = this.roleService.listRole();
        long total = pageUser.getTotal();

        model.addAttribute("pageTotal", total);
        model.addAttribute("roles", roles);
        return "role/index";
    }

    @RequestMapping("choosePerms")
    @ResponseBody
    public GlobalResult choosePerms(Long id) {
        List<Permission> permissions = this.permissionService.listPermission(true);
        List<RolePermission> rolePermissions = this.rolePermissionService.listRolePermissionByRoleId(id);
        Map<String, List<?>> retMap = new HashMap<>();
        retMap.put("permissions", permissions);
        retMap.put("rolePermissions", rolePermissions);
        return GlobalResult.build(1, "", retMap);
    }

    @RequestMapping(value = "choosePerms", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult choosePerms(Long roleId, String permsStr) {

        try {
            this.rolePermissionService.addRolePermission(roleId, permsStr);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            return GlobalResult.build(0, "操作失败");
        }
    }



    @RequestMapping("detail")
    @ResponseBody
    public GlobalResult detail(Long id) {
        try {
            Role role = this.roleService.findRoleById(id);
            if (role != null) {
                return GlobalResult.build(1, "roleDetail", role);
            }
            return GlobalResult.build(0, "不存在");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("addRole")
    @ResponseBody
    public GlobalResult addRole(Role role) {
        try {
            this.roleService.addRole(role);
            if (role != null) {
                return GlobalResult.build(1, "添加成功");
            }
            return GlobalResult.build(0, "添加失败");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("editRole")
    @ResponseBody
    public GlobalResult editRole(Role role) {
        try {
            this.roleService.updateRole(role);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

    @RequestMapping("delRole")
    @ResponseBody
    public GlobalResult delRole(Long id) {
        try {
            this.roleService.delRole(id);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "no msg");
        }
    }

}
