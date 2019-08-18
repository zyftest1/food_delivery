package com.mall.food.pojo;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
public class UserCustomer {
    private String userId;
    private String userName;
    private String password;
    private String tel;
    private String email;
    private String userKey;
    private String name;
    private Integer age;
    private String sex;
    private String address;
    private String idcard;
    private Date birthday;
    private String job;
    private BigDecimal balance;
    private Integer member;
}
