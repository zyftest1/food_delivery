package com.mall.food.controller;

import com.mall.food.pojo.CommodityType;
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
    @RequestMapping("/index.do")
    public String initPageShopList(Model model){
        List<CommodityType> commodityTypeList = commodityService.selectAllCommodityType();
        model.addAttribute("commodityTypes",commodityTypeList);
        return "index";
    }
}
