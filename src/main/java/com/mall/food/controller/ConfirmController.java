package com.mall.food.controller;

import com.mall.food.mapper.ShoppingCarMapper;
import com.mall.food.pojo.Order;
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
import org.thymeleaf.engine.DataDrivenTemplateIterator;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
public class ConfirmController {
    @Autowired
    private UserAddressService userAddressService;
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

    @RequestMapping("/confirm2")
    public String intoConfirm(HttpSession session, Model model) {
        BigDecimal total = new BigDecimal(0);
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        if (userCustomer == null ||userCustomer.equals("")){
            return "login";
        }else {

            List<UserAddress> addresses = userAddressService.getAll(userCustomer.getUserId());
        List<ShoppingCar> shoppingCars = shoppingCarMapper.selByUserId(userCustomer.getUserId());
        for (UserAddress address : addresses){
            String [] a = address.getAddress().split("_");
            address.setAddress(a[0]+a[1]+a[2]+a[3]);
        }
        for (ShoppingCar s:shoppingCars){
            total = total.add(s.getPrice());
        }
            Order order = Order.builder()
                    .ordName(userCustomer.getName())
                    .ordAddress(userCustomer.getAddress())
                    .ordTel(userCustomer.getTel()).build();
            model.addAttribute("addresses",addresses);
        model.addAttribute("shoppingCars",shoppingCars);
        model.addAttribute("total",total);
        model.addAttribute("order",order);

            return "confirm_order";}
    }

    @RequestMapping("/confirm2.do/{total}")
    public String confirm(){

        return "";
    }

}
