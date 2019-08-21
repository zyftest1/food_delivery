package com.mall.food.mapper;

import com.mall.food.pojo.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    @Select("select* from orders")
    public List<Order> selAllOrders();
    @Select("select* from orders where ord_id = {#ordId}")
    public Order selOrderById(Integer ordId);
    @Insert("insert into orders " +
            "(ord_name,ord_address,ord_tel,schedule,user_id,tel,com_id,com_name,price,quantity,b_id,b_name,b_tel,create_date) " +
            "values(#{ordName},#{ordAddress},#{ordTel},#{schedule},#{userId},#{tel},#{comId},#{comName},#{price},#{quantity},#{bId},#{bName},#{bTel},#{createDate})")
    public void insert(Order order);
}
