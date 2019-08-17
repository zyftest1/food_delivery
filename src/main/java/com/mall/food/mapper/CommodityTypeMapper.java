package com.mall.food.mapper;

import com.mall.food.pojo.CommodityType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityTypeMapper {
    @Select("select * from commodity_type")
    public List<CommodityType> selectAllType();
}
