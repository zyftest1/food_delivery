package com.mall.food.service;

import com.mall.food.mapper.AdvertisementMapper;
import com.mall.food.mapper.UserCustomerMapper;
import com.mall.food.pojo.Advertisement;
import com.mall.food.pojo.UserCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = "UserCustomerCache")
public class UserCustomerService {

    @Autowired
    private UserCustomerMapper userCustomerMapper;

    @Cacheable(value = "userCustomer",key = "targetClass+getMethodName()")
    public UserCustomer getUserCustomreByName(String userName){
        UserCustomer userCustomer = userCustomerMapper.selectUserCustomerByUserName(userName);
        if (userCustomer == null && userCustomer.equals("")){
            return null;
        }
        return userCustomer;
    }
}

