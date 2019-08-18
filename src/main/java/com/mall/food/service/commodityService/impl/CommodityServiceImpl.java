package com.mall.food.service.commodityService.impl;

import com.mall.food.mapper.CommodityMapper;
import com.mall.food.mapper.CommodityTypeMapper;
import com.mall.food.pojo.Commodity;
import com.mall.food.pojo.CommodityType;
import com.mall.food.service.commodityService.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements ICommodityService {
    @Autowired
    private CommodityTypeMapper commodityTypeMapper;
    @Autowired
    private CommodityMapper commodityMapper;
    @Override
    public List<CommodityType> selectAllCommodityType() {
        List<CommodityType> commodityTypeList = commodityTypeMapper.selectAllType();
        return commodityTypeList;
    }

    @Override
    public List<Commodity> selectAllCommodity() {
        List<Commodity> commodityList = commodityMapper.selectAll();
        return commodityList;
    }

    @Override
    public List<Commodity> selectByTypeCommodity(String commodityType) {
        List<Commodity> commodityList = commodityMapper.selectByType(commodityType);
        return commodityList;
    }
}
