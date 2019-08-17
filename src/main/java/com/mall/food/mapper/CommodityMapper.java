package com.mall.food.mapper;

import com.mall.food.pojo.Commodity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityMapper {
    @Select("select * from commodity")
    public List<Commodity> selectAll();
    @Select("select * from commodity where type = #{commodityType}")
    public List<Commodity> selectByType(String commodityType);
    @Select("select * from commodity where comId = #{commodityId}")
    public Commodity selectById(Integer commodityId);
}
