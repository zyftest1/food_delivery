package com.mall.food.mapper;

import com.mall.food.pojo.ShoppingCar;
import com.mall.food.pojo.UserCoupon;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Mapper
@Repository
public interface ShoppingCarMapper {
    @Select("select * from shopping_car")
    public List<ShoppingCar> selAllShoppingCars();

    @Select("select * from shopping_car where user_id = #{userId}")
    public List<ShoppingCar> selByUserId(String  userId);

    @Select("select * from shopping_car where car_id = #{carId}")
    public ShoppingCar selShoppingCarById(Integer carId);

    @Insert("insert into shopping_car(user_id,b_id,user_name,com_id,com_name,price,quantity,size,describes,picture,add_id,address)" +
            " values(#{userId},#{bId},#{userName},#{comId},#{comName},#{price},#{quantity},#{size},#{describes},#{picture},#{addId},#{address})")
    public void insert(ShoppingCar shoppingCar);

    @Update("update shopping_car set user_id = #{userId},b_id = #{bId},user_name = #{userName},com_name = #{comName}," +
            "price = #{price},quantity = #{quantity},size=#{size},describes =#{describes},picture=#{picture},add_id=#{addId},address=#{address}" +
            "where com_id = #{comId}")
    public void  updateShoppingCar(ShoppingCar shoppingCar);

    @Delete("delete from shopping_car where car_id = #{carId}")
    public void deleteById(Integer carId);



}
