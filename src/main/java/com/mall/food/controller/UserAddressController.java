package com.mall.food.controller;

import com.alibaba.fastjson.JSON;
import com.mall.food.pojo.*;
import com.mall.food.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class UserAddressController {

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("/address")
    public String address(Model model, HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        List<UserAddress> userAddressList = userAddressService.getAll(userCustomer.getUserId());
        for (UserAddress u:userAddressList) {
            String[] addressList = u.getAddress().split("_");
            u.setAddress(addressList[0]+addressList[1]+addressList[2]+addressList[3]);
        }
        model.addAttribute("userAddressList",userAddressList);
        return "user_address";
    }

    @RequestMapping(value = "/pro",method = RequestMethod.POST)
    @ResponseBody
    public Object province(){
        List<Province> provinces = userAddressService.getAllProvince();
        String pListJson = JSON.toJSONString(provinces);
        return pListJson;
    }

    @RequestMapping(value = "/city",method = RequestMethod.POST)
    @ResponseBody
    public String city(String provinceId){
        List<City> cities = userAddressService.getAllCity(Integer.valueOf(provinceId));
        String pListJson = JSON.toJSONString(cities);
        return pListJson;
    }

    @RequestMapping(value = "/area",method = RequestMethod.POST)
    @ResponseBody
    public String area(String cityId){
        List<Area> areas = userAddressService.getAllArea(Integer.valueOf(cityId));
        String pListJson = JSON.toJSONString(areas);
        return pListJson;
    }

    /**
     * 判断字符串是否为整数
     * @param str
     * @return
     */
    public static boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }

    @RequestMapping("/addAddress")
    public String addAddress(Model model,HttpSession session,String province,String city,
                             String area,String detailAddress,String name,String tel,String addId1){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        String pr = "";
        String ci = "";
        String ar = "";
        if (isInteger(province)) {
            Province p = userAddressService.getProvince(Integer.valueOf(province));
            pr = p.getProvince();
        }else {
            pr = province;
        }
        if (isInteger(city)) {
            City c = userAddressService.getCity(Integer.valueOf(city));
            ci = c.getCity();
        }else {
            pr = province;
        }
        if (isInteger(area)) {
            Area a = userAddressService.getArea(Integer.valueOf(area));
            ar = a.getArea();
        }else {
            ar = area;
        }
        System.out.println(addId1);
        String address = pr+"_"+ci+"_"+ar+"_"+detailAddress;
        if (addId1 == null||addId1.equals("")){
            UserAddress userAddress = UserAddress.builder().userId(userCustomer.getUserId()).address(address).name(name).tel(tel).build();
            userAddressService.insert(userAddress);
        }else {
            UserAddress userAddress = UserAddress.builder().addId(Integer.valueOf(addId1)).userId(userCustomer.getUserId()).address(address).name(name).tel(tel).build();
            userAddressService.update(userAddress);
        }
        List<UserAddress> userAddressList = userAddressService.getAll(userCustomer.getUserId());
        for (UserAddress u:userAddressList) {
            String[] addressList = u.getAddress().split("_");
            u.setAddress(addressList[0]+addressList[1]+addressList[2]+addressList[3]);
        }
        model.addAttribute("userAddressList",userAddressList);
        return "user_address";
    }

    @RequestMapping(value = "/updateAddress",method = RequestMethod.POST)
    @ResponseBody
    public Object updateAddress(Model model,HttpSession session,String addId){
        Map<String, String> map = new HashMap<>();
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        System.out.println(addId);
        UserAddress userAddress = userAddressService.getAddress(addId);
        String[] address = userAddress.getAddress().split("_");
        String province = address[0];
        String city = address[1];
        String area = address[2];
        String detailAddress = address[3];
        System.out.println(province+"--"+city+"---"+area+"---"+detailAddress);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("detailAddress",detailAddress);
        map.put("name",userAddress.getName());
        map.put("tel",userAddress.getTel());
        map.put("addId1",String.valueOf(userAddress.getAddId()));
        System.out.println(userAddress.getName()+"--"+userAddress.getTel());
        return map;
    }

    @RequestMapping("/deleteAddress")
    public String deleteAddress(Model model,HttpSession session,String addId){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        userAddressService.delete(Integer.valueOf(addId));
        List<UserAddress> userAddressList = userAddressService.getAll(userCustomer.getUserId());
        for (UserAddress u:userAddressList) {
            String[] addressList = u.getAddress().split("_");
            u.setAddress(addressList[0]+addressList[1]+addressList[2]+addressList[3]);
        }
        model.addAttribute("userAddressList",userAddressList);
        return "user_address";
    }
}
