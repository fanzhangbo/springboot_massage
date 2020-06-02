package com.fzb.massage.controller;

import com.fzb.massage.entity.Massage;
import com.fzb.massage.entity.User;
import com.fzb.massage.service.MassageService;
import com.fzb.massage.service.UserService;
import com.fzb.massage.utils.GlobalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@RestController
@RequestMapping("api/user")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public GlobalResult login(@RequestParam String username,
                              @RequestParam String password) {
        try {
            User user = this.userService.findUserByUsernameAndPassword(username, password);
            if (null != user) {
                return GlobalResult.build(1, "登录成功", user);
            }
            return GlobalResult.build(0, "登录失败，用户名或密码有误");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "登录失败，用户名或密码有误");
        }
    }
    @GetMapping("/detail")
    public GlobalResult detail(Long id) {
        try {
            User userById = this.userService.findUserById(id);
            return GlobalResult.build(1, "", userById);
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "操作失败");
        }
    }
    @PostMapping("changePass")
    public GlobalResult changePass(@RequestParam("id") Long id,
                                   @RequestParam("name") String name,
                                   @RequestParam("password") String password,
                                   @RequestParam("rePassword") String rePassword) {

        try {
            User user = this.userService.findUserByUsernameAndPassword(name, password);
            if (user != null && user.getId().equals(id)) {
                user.setPassword(rePassword);
                this.userService.updateUser(user);
            }
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "操作失败");
        }


    }

    @GetMapping("test")
    public GlobalResult test() {
        return GlobalResult.build(1, "测试");
    }

}
