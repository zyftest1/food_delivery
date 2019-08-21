package com.mall.food.pojo;
import lombok.Data;
import java.util.Date;

@Data
public class Order {
    private Integer ordId;
    private String ordName;
    private String ordAddress;
    private String ordTel;
    private String schedule;
    private String userId;
    private String tel;
    private Integer comId;
    private String comName;
    private Integer price;
    private Integer quantity;
    private String bId;
    private String bName;
    private String bTel;
    private Date createDate;
}
