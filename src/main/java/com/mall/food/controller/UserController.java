package com.mall.food.controller;

import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.AdministratorService;
import com.mall.food.service.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    private UserCustomerService userCustomerService;

    @RequestMapping("/login")
    public String findEmpAll(Model model,String userName,String password, HttpSession session){
        if (!StringUtils.isEmpty(userName)&& !StringUtils.isEmpty(password)){
            UserCustomer userCustomer = userCustomerService.getUserCustomreByName(userName);
            if (userCustomer.getPassword() == password){
                session.setAttribute("userCustomer",userCustomer);
                return  "index";
            }else {
                model.addAttribute("msg","密码错误，请重新输入");
            }
        }else{
            model.addAttribute("msg","用户名/密码不能为空");
            return  "index";
        }
        return "index";

    }

}
