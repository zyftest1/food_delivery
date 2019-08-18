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
public class UserCustomerService {

    @Autowired
    private UserCustomerMapper userCustomerMapper;

    public UserCustomer getUserCustomreByName(String userName){
        System.out.println(userName);
        UserCustomer userCustomer = userCustomerMapper.selectUserCustomerByUserName(userName);
        if (userCustomer != null){
            System.out.println(userCustomer);
            return userCustomer;
        }else {
            return null;
        }
    }

    public List<UserCustomer> getAll(){
        List<UserCustomer> userCustomerList = userCustomerMapper.selectAll();
        return userCustomerList;
    }

    public void insert(UserCustomer userCustomer){
        userCustomerMapper.insertUserCustomer(userCustomer);
    }
}

