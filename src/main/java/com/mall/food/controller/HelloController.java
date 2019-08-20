package com.mall.food.controller;


import com.mall.food.pojo.Administrator;
import com.mall.food.pojo.Advertisement;
import com.mall.food.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;

////@RestController  //使用注解形式
//@Controller
//@EnableAutoConfiguration
//public class HelloController {
//
////    @RequestMapping("/hello")
////    public String hello(){
////        return "hello spring Boot";
////    }
//
//    @RequestMapping("/")
//    public String hello(){
//        return "index";
//    }
//
//}
//@Controller
//public class detailspController {
//    @Autowired
//    CommodityMapper commodityMapper;
//    @RequestMapping("/detailsp")
//    public String selectByIdCommodity(Integer id, Model model){
//        Commodity commodity = commodityMapper.selectById(id);
//        System.out.println(commodity);
//        model.addAttribute("commodityDetail",commodity);
//        return "detailsp";
//    }
//        }

@Controller
public class HelloController {

    @Autowired
    private AdministratorService administratorService;
    @RequestMapping("/all")
    public String findEmpAll(Model model){
//        List<Advertisement> emps = administratorService.getAll();
//        model.addAttribute("emps",emps);
        return "about";
    }

}