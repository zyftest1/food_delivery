package com.mall.food.service.advertisementService.impl;

import com.mall.food.mapper.AdvertisementMapper;
import com.mall.food.pojo.Advertisement;
import com.mall.food.service.advertisementService.IAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdvertisementServiceImpl implements IAdvertisementService {
    @Autowired
    AdvertisementMapper advertisementMapper;
    @Override
    public List<Advertisement> selectAllAdvertisement() {
        List<Advertisement> advertisementList= advertisementMapper.selectAll();
        return advertisementList;
    }
}
