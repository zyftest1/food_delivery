package com.mall.food.controller;

import com.mall.food.pojo.Advertisement;
import com.mall.food.pojo.Business;
import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.CommodityType;
import com.mall.food.service.impl.AdvertisementServiceImpl;
import com.mall.food.service.impl.BusinessServiceImpl;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.apache.http.HttpRequest;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletRequest;
import javax.websocket.server.PathParam;
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
//    @RequestMapping("/selectByType")
//    @ResponseBody
//    public List<Commodity> selectCommodity(String cType){
//        System.out.println(cType);
//        List<Commodity> commodityList = commodityService.selectByTypeCommodity(cType);
//        System.out.println(commodityList);
//        return commodityList;
//    }
}
