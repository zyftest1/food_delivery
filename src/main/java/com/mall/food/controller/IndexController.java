package com.mall.food.controller;

import com.mall.food.pojo.Advertisement;
import com.mall.food.pojo.Business;
import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.CommodityType;
import com.mall.food.service.impl.AdvertisementServiceImpl;
import com.mall.food.service.impl.BusinessServiceImpl;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class IndexController {
    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    AdvertisementServiceImpl advertisementService;
    @Autowired
    BusinessServiceImpl businessService;
    @RequestMapping("/initIndex")
    public String initPageShopList(Model model){
        List<Advertisement> advertisementList = advertisementService.selectAllAdvertisement();
        model.addAttribute("advertisements",advertisementList);
        List<CommodityType> commodityTypeList = commodityService.selectAllCommodityType();
        model.addAttribute("commodityTypes",commodityTypeList);
        List<Commodity> commodityList = commodityService.selectForIndexCommodity(6);
        model.addAttribute("commoditys",commodityList);
        List<Business> businessList = businessService.selectForIndexBusiness(3);
        model.addAttribute("businesss",businessList);
        return "index";
    }
}
