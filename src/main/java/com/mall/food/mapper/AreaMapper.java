package com.mall.food.mapper;

import com.mall.food.pojo.Area;
import com.mall.food.pojo.City;
import com.mall.food.pojo.Province;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AreaMapper {
    @Select("select * from areas where area_id = #{areaId}")
    public Area selectAreaByAId(Integer areaId);

    @Select("select * from areas where city_id = #{cityId}")
    public List<Area> selectAreasByCId(Integer cityId);
}
