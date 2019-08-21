package com.mall.food.controller;

import com.mall.food.pojo.Business;
import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.CommodityType;
import com.mall.food.service.impl.BusinessServiceImpl;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ListController {
    @Autowired
    CommodityServiceImpl commodityService;
    @Autowired
    BusinessServiceImpl businessService;
    @RequestMapping("/tolist")
    public String initListPage(Model model){
        List<Commodity> commodityList= commodityService.selectAllCommodity();
        model.addAttribute("allCommodity",commodityList);
        List<CommodityType> commodityTypeList = commodityService.selectAllCommodityType();
        model.addAttribute("cTypes",commodityTypeList);
        System.out.println(commodityList);
        return "list";
    }
    @RequestMapping("/toShopList")
    public String initShopList(Model model){
        List<Business> businessList = businessService.selectAllBusiness();
        model.addAttribute("allBusiness",businessList);
        System.out.println(businessList);
        return "business";
    }
}
