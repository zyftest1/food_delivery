package com.mall.food.service;

import com.mall.food.mapper.UserAddressMapper;
import com.mall.food.pojo.UserAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    public List<UserAddress> getAll(String userId){
        List<UserAddress> userAddressList = userAddressMapper.selectAll(userId);
        return userAddressList;
    }
}
