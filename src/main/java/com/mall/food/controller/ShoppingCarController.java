package com.mall.food.controller;

import com.mall.food.mapper.ShoppingCarMapper;
import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.ShoppingCar;
import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.ShoppingCarService;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ShoppingCarController {
    @Autowired
    private ShoppingCarService shoppingCarService;

    @Autowired
    private CommodityServiceImpl commodityService;

    @Autowired
    private ShoppingCarMapper shoppingCarMapper;

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

    @RequestMapping(value = "/updateCar",method = RequestMethod.POST)
    @ResponseBody
    public Object updateCar(Model model, HttpSession session,String quantity,String carId){
        Map<String, String> map = new HashMap<>();
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        System.out.println(quantity);
//        System.out.println(commodityDetail);
        ShoppingCar shoppingCar = shoppingCarService.selectById(Integer.valueOf(carId));
        System.out.println(shoppingCar);
        shoppingCar.setQuantity(Integer.valueOf(quantity));
        shoppingCarService.update(shoppingCar);
        map.put("msg","数量修改成功");
        return map;
    }

    @RequestMapping("/confirm")
    public String confirm(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "confirm_order";
    }

    @RequestMapping(value = "/deleteShoppingCar")
    public String deleteShoppingCar(Model model, HttpSession session,String quantity,String carId){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        System.out.println(quantity);
//        System.out.println(commodityDetail);
        shoppingCarMapper.deleteById(Integer.valueOf(carId));
        List<ShoppingCar> shoppingCarList = shoppingCarService.getAll();
        List<ShoppingCar>[] lists = null;
//        for (ShoppingCar s:shoppingCarList) {
//
//        }
        model.addAttribute("shoppingCarList",shoppingCarList);
        return "cart";
    }
}
