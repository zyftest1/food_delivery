package com.mall.food.mapper;

import com.mall.food.pojo.Province;
import com.mall.food.pojo.UserCoupon;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ProvinceMapper {
    @Select("select * from provinces where province_id = #{provinceId}")
    public Province selectProvinceById(Integer provinceId);

    @Select("select * from provinces")
    public List<Province> selectAll();

}
