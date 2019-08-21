package com.mall.food.controller;

import com.mall.food.mapper.ShoppingCarMapper;
import com.mall.food.pojo.ShoppingCar;
import com.mall.food.pojo.UserAddress;
import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.UserAddressService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class ConfirmController {
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;
    @RequestMapping("/confirm")
    public String intoConfirm(HttpSession session, Model model) {
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        List<UserAddress> addresses = userAddressService.getAll(userCustomer.getUserId());
//        List<ShoppingCar> shoppingCars = shoppingCarMapper.selShoppingCarById()
        for (UserAddress address : addresses){
            String [] a = address.getAddress().split("_");
            address.setAddress(a[1]+a[2]+a[3]+a[4]);
        }

        model.addAttribute("addresses",addresses);

            return "cinfirm_order";
    }

    @RequestMapping("/confirm.do")
    public String confirm(){
        return "";
    }

}
