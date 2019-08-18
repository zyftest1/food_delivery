package com.mall.food.service.commodityService.impl;

import com.mall.food.mapper.CommodityTypeMapper;
import com.mall.food.pojo.CommodityType;
import com.mall.food.service.commodityService.ICommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements ICommodityService {
    @Autowired
    private CommodityTypeMapper commodityTypeMapper;
    @Override
    public List<CommodityType> selectAllCommodityType() {
        List<CommodityType> commodityTypeList = commodityTypeMapper.selectAllType();
        return commodityTypeList;
    }
}
