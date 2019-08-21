package com.mall.food.controller;

import com.mall.food.mapper.CommodityMapper;
import com.mall.food.pojo.Advertisement;
import com.mall.food.pojo.Commodity;
import com.mall.food.service.impl.AdvertisementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class LikeSearchController {

    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    AdvertisementServiceImpl advertisementService;

    @RequestMapping("/like")
    public String selectByIdCommodity(String com_name, Model model){
        List<Commodity> commodity = commodityMapper.selectByLike(com_name);
        System.out.println("模糊查询商品::::"+commodity);
        model.addAttribute("commodityLike",commodity);
        List<Advertisement> advertisementList = advertisementService.selectAllAdvertisement();
        model.addAttribute("advertisements",advertisementList);
        return "like";
    }

}
