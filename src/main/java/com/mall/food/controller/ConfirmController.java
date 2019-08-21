package com.mall.food.controller;

import com.mall.food.mapper.CouponMapper;
import com.mall.food.mapper.OrderMapper;
import com.mall.food.mapper.ShoppingCarMapper;
import com.mall.food.mapper.UserCouponMapper;
import com.mall.food.pojo.*;
import com.mall.food.service.UserAddressService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sun.org.apache.xpath.internal.operations.Or;
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
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CouponMapper couponMapper;
    @Autowired
    private UserCouponMapper userCouponMapper;

    Order order = null;
    @RequestMapping("/confirm2")
    public String intoConfirm(HttpSession session, Model model) {
        int i = 0;
        BigDecimal total = new BigDecimal(0);
        Date date = new Date();
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
            i++;
        }
            List<UserCoupon> userCoupons = userCouponMapper.selectUserCouponById(userCustomer.getUserId());
            Order order = Order.builder()
                    .ordName(userCustomer.getName())
                    .ordAddress("中北大学文瀛苑九号楼")
                    .ordTel(userCustomer.getTel())
                    .schedule("已提交")
                    .userId(userCustomer.getUserId())
                    .tel(userCustomer.getTel())
                    .comId(10001104)
                    .comName("刀削面肉酱卤")
                    .price(total)
                    .quantity(i)
                    .bId("1001121")
                    .bTel(userCustomer.getTel())
                    .bName("")
                    .createDate(date).build();
        orderMapper.insert(order);
        model.addAttribute("addresses",addresses);
        model.addAttribute("shoppingCars",shoppingCars);
        model.addAttribute("total",total);
        model.addAttribute("order",order);
        model.addAttribute("userCoupons",userCoupons);

            return "confirm_order";}
    }

//    @RequestMapping(value = "/confirm2.do")
//    public Order confirm(HttpSession session,Model model){
//        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
//        order.setOrdAddress(couponMapper.deleteById(););
//        return order;
//    }

}
