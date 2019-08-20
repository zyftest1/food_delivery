package com.mall.food.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserAddress {
    private Integer addId;
    private String userId;
    private String address;
    private String name;
    private String tel;

}
