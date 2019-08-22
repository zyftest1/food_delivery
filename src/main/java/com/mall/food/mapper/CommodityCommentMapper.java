package com.mall.food.mapper;

import com.mall.food.pojo.CommodityComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityCommentMapper {
    @Select("select * from commodity_comment limit 4")
    List<CommodityComment> selectWithLimit();
}
