package com.mall.food.service;

import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.CommodityType;

import java.util.List;

public interface ICommodityService {
     List<CommodityType> selectAllCommodityType();
     List<Commodity> selectAllCommodity();
     List<Commodity> selectByTypeCommodity(String commodityType);
     List<Commodity> selectForIndexCommodity(Integer number);

     Commodity selectById(Integer comId);
}
