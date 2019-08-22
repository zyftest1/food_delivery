package com.mall.food.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.food.mapper.OrderMapper;
import com.mall.food.pojo.Order;
import com.mall.food.pojo.UserCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderlistController {

    @Autowired
    private OrderMapper orderMapper;

    @RequestMapping("/order")
    public String order(Model model, HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_order";
    }

    @RequestMapping("/orderlist")
    public String orderlist(Model model,@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");

        if (userCustomer == null || "".equals(userCustomer)){
            return "/login";
        }
            String userId = userCustomer.getUserId();
            PageHelper.startPage(pageNum,5);
            List<Order> orders = orderMapper.selectAll(userId);
            System.out.println(orders);
            PageInfo<Order> page = new PageInfo<>(orders);
            model.addAttribute("page",page);
            return "user_orderlist";
    }

    @RequestMapping("/orderT")
    public String orders(Model model){

        return "user_orderlist";
    }

    @RequestMapping("/upOrders")
    @ResponseBody
    public Object orders(Integer ordId){
        System.out.println(ordId);
        Order order = orderMapper.selOrderById(ordId);
        order.setSchedule("完结");
        System.out.println(order);
        orderMapper.update(order);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","收货成功");
        return map;
    }



}
