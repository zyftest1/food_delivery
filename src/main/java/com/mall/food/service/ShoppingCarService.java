package com.mall.food.service;

import com.mall.food.mapper.ShoppingCarMapper;
import com.mall.food.pojo.ShoppingCar;
import com.mall.food.pojo.UserCustomer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCarService {
    @Autowired
    private ShoppingCarMapper shoppingCarMapper;


    public List<ShoppingCar> getAll(){
        List<ShoppingCar> shoppingCarList = shoppingCarMapper.selAllShoppingCars();
        return shoppingCarList;
    }

    public void insert(ShoppingCar shoppingCar){
        shoppingCarMapper.insert(shoppingCar);
    }

//    public void update(UserCustomer userCustomer){
//        userCustomerMapper.updateUserCustomer(userCustomer);
//    }
}
