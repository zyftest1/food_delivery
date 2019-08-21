package com.mall.food.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.food.pojo.*;
import com.mall.food.service.CouponService;
import com.mall.food.service.impl.AdvertisementServiceImpl;
import com.mall.food.service.impl.BusinessServiceImpl;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private CouponService couponService;
    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    AdvertisementServiceImpl advertisementService;
    @Autowired
    BusinessServiceImpl businessService;
    @RequestMapping("/")
    public String initPageShopList(Model model, HttpSession session, @RequestParam(value = "p",defaultValue = "1") Integer pages){
        List<Advertisement> advertisementList = advertisementService.selectAllAdvertisement();
        model.addAttribute("advertisements",advertisementList);
        List<CommodityType> commodityTypeList = commodityService.selectAllCommodityType();
        model.addAttribute("commodityTypes",commodityTypeList);
        List<Commodity> commodityList = commodityService.selectForIndexCommodity(6);
        model.addAttribute("commoditys",commodityList);
        List<Business> businessList = businessService.selectForIndexBusiness(3);
        model.addAttribute("businesss",businessList);
        //优惠券的展示
        PageHelper.startPage(pages,5);
        List<Coupon> list=couponService.getAll();
        PageInfo<Coupon> page=new PageInfo<>(list);
        model.addAttribute("page",page);

        if (session.getAttribute("userCustomer") != null){
            UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
            session.setAttribute("userCustomer",userCustomer);
        }
        return "index";
    }
}
