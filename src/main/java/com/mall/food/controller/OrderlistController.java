package com.mall.food.controller;

import com.mall.food.pojo.UserCustomer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class OrderlistController {

    @RequestMapping("/order")
    public String order(Model model, HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_order";
    }

    @RequestMapping("/orderlist")
    public String orderlist(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_orderlist";
    }
}
