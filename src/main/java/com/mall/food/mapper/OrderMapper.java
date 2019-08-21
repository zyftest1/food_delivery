package com.mall.food.mapper;

import com.mall.food.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface OrderMapper {
    @Select("select * from orders")
    public List<Order> selAllOrders();

    @Select("select * from orders where ord_id = #{ordId}")
    public Order selOrderById(Integer ordId);

    @Update("update orders set ord_name = #{ordName},ord_address = #{ordAddress},ord_tel = #{ordTel},schedule = #{schedule}," +
            "user_id = #{userId},tel = #{tel},com_id = #{comId},com_name = #{comName},price = #{price}," +
            "quantity = #{quantity},b_id = #{bId},b_name = #{bName},b_tel = #{bTel},create_date = #{createDate} where ord_id = #{ordId}")
    public void update(Order order);
}
