package com.mall.food.controller;

import com.mall.food.mapper.CommodityMapper;
import com.mall.food.pojo.Business;
import com.mall.food.pojo.Commodity;
import com.mall.food.service.impl.BusinessServiceImpl;
import com.mall.food.service.impl.CommodityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DetailController {

    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    BusinessServiceImpl businessService;
    @RequestMapping("/detail")
    public String selectByIdCommodity(Integer comId, Model model){
        Commodity commodity = commodityMapper.selectById(comId);
        System.out.println(commodity);
        model.addAttribute("commodityDetail",commodity);
        return "detailsp";
    }
    @RequestMapping("/toShop")
    public String selectByIdBusiness(String bId, Model model){
        Business business = businessService.selectById(bId);
        model.addAttribute("shopDetail",business);
        List<Commodity> commodityList = commodityMapper.selectByBId(bId);
        model.addAttribute("commoditysByBId",commodityList);
        return "shop";
    }

}
