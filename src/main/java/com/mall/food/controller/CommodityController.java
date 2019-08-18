package com.mall.food.controller;

import com.mall.food.pojo.Advertisement;
import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.CommodityType;
import com.mall.food.service.advertisementService.impl.AdvertisementServiceImpl;
import com.mall.food.service.commodityService.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class CommodityController {
    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    AdvertisementServiceImpl advertisementService;
    @RequestMapping("/initIndex")
    public String initPageShopList(Model model){
        List<Advertisement> advertisementList = advertisementService.selectAllAdvertisement();
        model.addAttribute("advertisements",advertisementList);
        List<CommodityType> commodityTypeList = commodityService.selectAllCommodityType();
        model.addAttribute("commodityTypes",commodityTypeList);
        return "index";
    }
}
