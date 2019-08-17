package com.mall.food.controller;

import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.AdministratorService;
import com.mall.food.service.UserCustomerService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserCustomerService userCustomerService;

    @RequestMapping("/login")
    public String userLogin(Model model,String userName,String password, HttpSession session){
        if (!StringUtils.isEmpty(userName)&& !StringUtils.isEmpty(password)){
            UserCustomer userCustomer = userCustomerService.getUserCustomreByName(userName);
            if (userCustomer == null){
                model.addAttribute("msg","用户名错误，请重新输入");
                return "login";
            }else if(!userCustomer.getPassword().equals(password)) {
                System.out.println(userCustomer.getPassword());
                model.addAttribute("msg","密码错误，请重新输入");
                return "login";
            }else {
                session.setAttribute("userCustomer",userCustomer);
                return  "user_center";
            }
        }else {
            model.addAttribute("msg","用户名/密码不能为空");
            return  "login";
        }
    }

    @RequestMapping("/confirm")
    public void confirmUserName(Model model,String userName){
        System.out.println(userName);
        List<UserCustomer> userCustomerList = userCustomerService.getAll();
        String msg ="";
        for (UserCustomer userCustomer:userCustomerList) {
            if (userCustomer.getUserName().equals(userName)){
                model.addAttribute("msg","用户名被占用");
            }else{
                model.addAttribute("msg","用户名可用");
            }
        }
    }

}
