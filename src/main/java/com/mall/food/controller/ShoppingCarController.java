package com.mall.food.controller;

import com.mall.food.pojo.UserCustomer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ShoppingCarController {
    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "cart";
    }

    @RequestMapping("/confirm")
    public String confirm(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "confirm_order";
    }
}
