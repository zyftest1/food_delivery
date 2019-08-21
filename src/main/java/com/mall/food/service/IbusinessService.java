package com.mall.food.service;

import com.mall.food.pojo.Business;

import java.util.List;

public interface IbusinessService {
    List<Business> selectForIndexBusiness(Integer number);
    List<Business> selectAllBusiness();
    Business selectById(String id);
}
