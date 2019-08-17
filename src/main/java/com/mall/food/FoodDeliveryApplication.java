package com.mall.food;

import com.mall.food.mapper.UserCustomerMapper;
import com.mall.food.pojo.UserCustomer;
import org.junit.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan("com.mall.food.mapper")
@EnableCaching
@SpringBootApplication
public class FoodDeliveryApplication {

    @Autowired
    UserCustomerMapper mapper;
    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);


    }

}
