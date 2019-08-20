package com.mall.food.service;

import com.mall.food.mapper.AreaMapper;
import com.mall.food.mapper.CityMapper;
import com.mall.food.mapper.ProvinceMapper;
import com.mall.food.mapper.UserAddressMapper;
import com.mall.food.pojo.Area;
import com.mall.food.pojo.City;
import com.mall.food.pojo.Province;
import com.mall.food.pojo.UserAddress;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAddressService {
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private ProvinceMapper provinceMapper;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private AreaMapper areaMapper;

    public UserAddress getAddress(String addId){
        UserAddress userAddress = userAddressMapper.selectUserAddressById(Integer.valueOf(addId));
        return userAddress;
    }

    public List<UserAddress> getAll(String userId){
        List<UserAddress> userAddressList = userAddressMapper.selectAll(userId);
        return userAddressList;
    }

    public void insert(UserAddress userAddress){
        userAddressMapper.insertUserAddress(userAddress);
    }

    public void update(UserAddress userAddress){
        userAddressMapper.updateUserAddress(userAddress);
    }

    public void delete(Integer addId){
        userAddressMapper.deleteUserAddressByAddId(addId);
    }



    public List<Province> getAllProvince(){
        List<Province> provinces = provinceMapper.selectAll();
        return provinces;
    }

    public Province getProvince(Integer provinceId){
        Province province = provinceMapper.selectProvinceById(provinceId);
        return province;
    }

    public List<City> getAllCity(Integer provinceId){
        List<City> cities = cityMapper.selectCityByPId(provinceId);
        return cities;
    }

    public City getCity(Integer cityId){
        City city = cityMapper.selectCityByCId(cityId);
        return city;
    }

    public List<Area> getAllArea(Integer cityId){
        List<Area> areas = areaMapper.selectAreasByCId(cityId);
        return areas;
    }

    public Area getArea(Integer areaId){
        Area area = areaMapper.selectAreaByAId(areaId);
        return area;
    }
}
