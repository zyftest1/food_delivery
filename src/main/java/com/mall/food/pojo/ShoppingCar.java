package com.mall.food.pojo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ShoppingCar {
    private Integer carId;
    private String userId;
    private String userName;
    private String bId;
    private Integer comId;
    private String comName;
    private BigDecimal price;
    private Integer quantity;
    private String size;
    private String describes;
    private String picture;
    private Integer addId;
    private String address;
}
