package com.mall.food.controller;

import com.mall.food.mapper.CommodityMapper;
import com.mall.food.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DetailController {

    @Autowired
    CommodityMapper commodityMapper;
    @RequestMapping("/detail")
    public String selectByIdCommodity(Integer comId, Model model){
        Commodity commodity = commodityMapper.selectById(comId);
        System.out.println(commodity);
        model.addAttribute("commodityDetail",commodity);
        return "detailsp";
    }

}
