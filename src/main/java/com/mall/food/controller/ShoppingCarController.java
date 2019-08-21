package com.mall.food.controller;

import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.ShoppingCar;
import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.ShoppingCarService;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ShoppingCarController {
    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private CommodityServiceImpl commodityService;

    @RequestMapping("/cart")
    public String cart(Model model, HttpSession session,String comId1,String Number){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        if (userCustomer == null ||userCustomer.equals("")){
            return "/login";
        }else {
            session.setAttribute("userCustomer",userCustomer);
            System.out.println(comId1);
//        System.out.println(commodityDetail);
            Commodity commodity = commodityService.selectById(Integer.valueOf(comId1));
            System.out.println(commodity);
            ShoppingCar shoppingCar = ShoppingCar.builder().userId(userCustomer.getUserId()).userName(userCustomer.getUserName()).bId(commodity.getBId()).comId(commodity.getComId()).comName(commodity.getComName()).price(commodity.getPrice()).quantity(Integer.valueOf(Number)).size(commodity.getSize()).describes(commodity.getDescribes()).picture(commodity.getPicture()).build();
            System.out.println(shoppingCar);
            shoppingCarService.insert(shoppingCar);
            List<ShoppingCar> shoppingCarList = shoppingCarService.getAll();
            List<ShoppingCar>[] lists = null;
//        for (ShoppingCar s:shoppingCarList) {
//
//        }
            model.addAttribute("shoppingCarList",shoppingCarList);
            return "cart";
        }
    }

    @RequestMapping("/confirm")
    public String confirm(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "confirm_order";
    }
}
