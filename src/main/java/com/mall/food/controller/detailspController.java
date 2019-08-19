package com.mall.food.controller;

import com.mall.food.mapper.CommodityMapper;
import com.mall.food.pojo.Commodity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class detailspController {
    @Autowired
    CommodityMapper commodityMapper;
    @RequestMapping("/detailsp")
    public String selectByIdCommodity(Integer id, Model model){
        Commodity commodity = commodityMapper.selectById(id);
        System.out.println(commodity);
        model.addAttribute("commodityDetail",commodity);
        return "detailsp";
    }

}
