package com.mall.food.service.impl;

import com.mall.food.mapper.BusinessMapper;
import com.mall.food.pojo.Business;
import com.mall.food.service.IbusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BusinessServiceImpl implements IbusinessService {
    @Autowired
    BusinessMapper businessMapper;
    @Override
    public List<Business> selectForIndexBusiness(Integer number) {
        List<Business> businessList = businessMapper.selectByLimit(number);
        return businessList;
    }

    @Override
    public List<Business> selectAllBusiness() {
        List<Business> businessList = businessMapper.selectAll();
        return businessList;
    }

    @Override
    public Business selectById(String id) {
        Business business = businessMapper.selectBusinessById(id);
        return business;
    }

}
