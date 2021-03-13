package com.fzb.massage.controller;

import com.fzb.massage.utils.RandomValidateCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * TODO
 *
 * @author Fan Zhangbo
 */
@Controller
public class IndexController {

    /**
     * 登录页面生成验证码
     */
    @RequestMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("image/jpeg");//设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");//设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        RandomValidateCode randomValidateCode = new RandomValidateCode();
        try {
            randomValidateCode.getRandcode(request, response);//输出验证码图片方法
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("index")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("login")
    public String login(Model model) {
        model.addAttribute("msg", "");
        return "login";
    }

    @RequestMapping(value = "/doLogin",method = RequestMethod.POST)
    public String doLogin(String username, String password, Model model,
                          HttpSession session, String inputStr) {

        // 从session中获取验证码
        String random = (String) session.getAttribute("RANDOMVALIDATECODEKEY");
        System.out.println(inputStr);
        System.out.println(random);
        if(random == null || !random.equalsIgnoreCase(inputStr)){

            model.addAttribute("msg", "验证码错误");
            return "login";
        }
        // 获取当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            return "redirect:index";
        }catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unauthorize() {
        return "未经授权无法访问";
    }

    @RequestMapping("/logout")
    public String logout() {
        SecurityUtils.getSubject().logout();
        return "redirect:login";
    }

}
