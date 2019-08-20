package com.mall.food.mapper;

import com.mall.food.pojo.City;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CityMapper {
    @Select("select * from cities where city_id = #{cityId}")
    public City selectCityByCId(Integer cityId);

    @Select("select * from cities where province_id = #{provinceId}")
    public List<City> selectCityByPId(Integer provinceId);
}
