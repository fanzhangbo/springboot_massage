package com.fzb.massage.controller;

import com.fzb.massage.entity.Account;
import com.fzb.massage.entity.MassageOrder;
import com.fzb.massage.entity.User;
import com.fzb.massage.service.AccountService;
import com.fzb.massage.service.MassageOrderService;
import com.fzb.massage.service.UserService;
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
@RequestMapping("user")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private MassageOrderService massageOrderService;

    @Autowired
    private AccountService accountService;

    @RequestMapping("index")
    public String index(Model model, Integer pageNum,
                        @RequestParam(required = false) String search_field,
                        @RequestParam(required = false) String kw) {

        if (null == pageNum) {
            pageNum = 1;
        }
        Page<User> pageUser = PageHelper.startPage(pageNum, 6);
        Map<String, Object>  condition = new HashMap<>();

        if (null != kw && !("".equals(kw))) {
            if ("phone".equals(search_field)) {
                condition.put("phone", kw);
            } else if ("name".equals(search_field)) {
                condition.put("username", kw);
            }
        }
        List<User> users = this.userService.listUser(condition);
        long total = pageUser.getTotal();

        model.addAttribute("pageTotal", total);
        model.addAttribute("users", users);
        return "user/index";
    }

    @RequestMapping("detail")
    @ResponseBody
    public GlobalResult detail(Long id) {
        try {
            User userById = this.userService.findUserById(id);
            if (userById != null) {
                return GlobalResult.build(1, "userDetail", userById);
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
        List<User> users = this.userService.listUser(null);

        return GlobalResult.build(1, "success", users);
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult addUser(User user) {
        try {
            this.userService.addUser(user);
            return GlobalResult.build(1, "添加成功");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "添加失败");
        }
    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult editUser(User user) {
        try {
            this.userService.updateUser(user);
            return GlobalResult.build(1, "编辑成功");

        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "编辑失败");
        }
    }



    @RequestMapping(value = "delUser")
    @ResponseBody
    public GlobalResult addUser(Long id) {
        try {
            this.userService.delUser(id);
            return GlobalResult.build(1, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "删除失败");
        }
    }

    @RequestMapping("order")
    public String order(Model model, Long userId, Integer pageNum) {
        Map<String, Object>  condition = new HashMap<>();
        condition.put("userId", userId);
        if (null == pageNum) {
            pageNum = 1;
        }
        Page<MassageOrder> pageMassageOrder = PageHelper.startPage(pageNum, 6);
        List<MassageOrder> massageOrders = this.massageOrderService.listTotalMassageOrder(condition);

        model.addAttribute("pageTotal", pageMassageOrder.getTotal());
        model.addAttribute("massageOrders", massageOrders);
        return "user/order";
    }

    @RequestMapping("orderDetail")
    @ResponseBody
    public GlobalResult orderDetail(Long id) {
        try {
            MassageOrder massageOrderById = this.massageOrderService.findMassageOrderById(id);
            return GlobalResult.build(1, "订单详情", massageOrderById);
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "查询失败");
        }
    }

    @RequestMapping(value = "editOrder", method = RequestMethod.POST)
    @ResponseBody
    public GlobalResult editOrder(MassageOrder massageOrder) {
        try {
            this.massageOrderService.updateMassageOrder(massageOrder);

            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "操作失败");
        }
    }


    @RequestMapping("account")
    public String account(Model model, Long userId, Integer pageNum) {
        Map<String, Object>  condition = new HashMap<>();
        condition.put("userId", userId);
        if (null == pageNum) {
            pageNum = 1;
        }
        Page<Account> pageMassageOrder = PageHelper.startPage(pageNum, 6);
        List<Account> accounts = this.accountService.listAccount(condition);

        model.addAttribute("pageTotal", pageMassageOrder.getTotal());
        model.addAttribute("accounts", accounts);
        User userById = this.userService.findUserById(userId);
        model.addAttribute("userMoney", userById.getMoney());

        return "user/account";
    }

    @RequestMapping("addAccount")
    @ResponseBody
    public GlobalResult addAccount(Account account) {
        try {
            this.accountService.addAccount(account);
            return GlobalResult.build(1, "操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return GlobalResult.build(0, "操作失败");
        }
    }
}
