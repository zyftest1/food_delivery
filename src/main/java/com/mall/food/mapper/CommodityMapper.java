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
    @Select("select * from commodity where type = #{commodityType} limit 6")
    public List<Commodity> selectByType(String commodityType);
    @Select("select * from commodity where com_id = #{commodityId}")
    public Commodity selectById(Integer commodityId);
    @Select("select * from commodity limit #{number}")
    public List<Commodity> selectByLimit(Integer number);
    @Select("select * from commodity where b_id = #{bId}")
    public List<Commodity> selectByBId(String bId);

    @Select("select * from commodity where com_name like concat('%',#{com_name},'%') limit 6")
    public List<Commodity> selectByLike(String com_name);

}
