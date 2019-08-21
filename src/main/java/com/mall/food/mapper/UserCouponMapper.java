package com.mall.food.mapper;

import com.mall.food.pojo.UserCoupon;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserCouponMapper {
    @Select("select * from user_coupon where user_id = #{userId}")
    public List<UserCoupon> selectUserCouponById(String userId);

    @Select("select * from user_coupon")
    public List<UserCoupon> selectAll();

    @Insert("insert into user_coupon(coupon,user_id,describes,icon) values(#{coupon},#{userId},#{describes},#{icon}) ")
    public void insertUserCoupon(UserCoupon userAddress);

    @Update("update user_coupon set coupon = #{coupon},user_id = #{userId},describes = #{describes} icon=#{icon} where coupon_id = #{couponId}")
    public void updateUserCoupon(Integer addId);

    @Delete("delete from user_coupon where coupon_id = #{couponId}")
    public void deleteUserCouponByAddId(Integer addId);
}
