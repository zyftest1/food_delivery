package com.mall.food.controller;

import com.alibaba.fastjson.JSON;
import com.mall.food.pojo.*;
import com.mall.food.service.UserAddressService;
import com.mall.food.service.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@Controller
public class UserController {
    @Autowired
    private UserCustomerService userCustomerService;

    @Autowired
    private UserAddressService userAddressService;

    @RequestMapping("/read")
    public String read(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        if (userCustomer != null){
            session.setAttribute("userCustomer",userCustomer);
        }
        return "article_read";
    }
    @RequestMapping("/cart")
    public String cart(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "cart";
    }
    @RequestMapping("/category")
    public String category(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "category";
    }
    @RequestMapping("/confirm")
    public String confirm(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "confirm_order";
    }
    @RequestMapping("/detailsp")
    public String detailsp(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "detailsp";
    }
    @RequestMapping("/index")
    public String index(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "initIndex";
    }
    @RequestMapping("/list")
    public String list(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "list";
    }
    @RequestMapping("/login")
    public String userLogin(Model model, String userName, String password, HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "login";
    }

    @RequestMapping("/loginUser")
    public String loginUser(Model model, String userName, String password, HttpSession session){
        if (!StringUtils.isEmpty(userName)&& !StringUtils.isEmpty(password)){
            UserCustomer userCustomer = userCustomerService.getUserCustomreByName(userName);
            if (userCustomer == null){
                model.addAttribute("msg","用户名错误，请重新输入");
                return "login";
            }else if(!userCustomer.getPassword().equals(password)) {
                System.out.println(userCustomer.getPassword());
                model.addAttribute("msg","密码错误，请重新输入");
                return "login";
            }else {
                session.setAttribute("userCustomer",userCustomer);
                return  "user_center";
            }
        }else {
            model.addAttribute("msg","用户名/密码不能为空");
            return  "login";
        }
    }

    @RequestMapping(value = "/confirmUserName",method = RequestMethod.POST)
    @ResponseBody
    private Object confirmUserName(Model model,String userName,String password,String password2){
        List<UserCustomer> userCustomerList = userCustomerService.getAll();
        Map<String, String> map = new HashMap<>();
        for (UserCustomer userCustomer : userCustomerList) {
            if (userCustomer.getUserName().equals(userName)) {
                map.put("msg","用户名不可用");
                return map;
            }
        }
        map.put("msg","用户名可用");
        return map;
    }

    @RequestMapping(value = "/registerUser")
    private String registerUser(Model model,String userName,String password,String password2,String email,String tel,String captcha){
        if (!password.equals(password2)) {
            model.addAttribute("msg2", "密码不相等,请重新输入");
            return "register";
        }
        UserCustomer userCustomer = UserCustomer.builder().userId(tel).userName(userName).password(password).email(email).tel(tel).userKey("123456").balance(new BigDecimal(0.00)).member(0).build();
        userCustomerService.insert(userCustomer);
        return "register";
    }
    @RequestMapping("/register")
    public String register(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "register";
    }
    @RequestMapping("/shop")
    public String shop(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "shop";
    }
    @RequestMapping("/account")
    public String account(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        model.addAttribute("userName",userCustomer.getUserName());
        model.addAttribute("password",userCustomer.getPassword());
        model.addAttribute("email",userCustomer.getEmail());
        model.addAttribute("tel",userCustomer.getTel());
        model.addAttribute("name",userCustomer.getName());
        model.addAttribute("age",userCustomer.getAge());
        model.addAttribute("idcard",userCustomer.getIdcard());
        return "user_account";
    }
    @RequestMapping("/updateAccount")
    public String updateAccount(Model model,HttpSession session,String userName,String email,String tel,String password,String name,String age,String idcard){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        userCustomer.setUserName(userName);
        userCustomer.setEmail(email);
        userCustomer.setTel(tel);
        userCustomer.setPassword(password);
        userCustomer.setName(name);
        userCustomer.setAge(Integer.valueOf(age));
        userCustomer.setIdcard(idcard);
        userCustomerService.update(userCustomer);
        return "/account";
    }

    @RequestMapping(value = "/updateMember")
    @ResponseBody
    public Object updateBalance(HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        Map<String, String> map = new HashMap<>();
        BigDecimal balances = userCustomer.getBalance();
        BigDecimal i = new BigDecimal(188.00);
        if (balances.compareTo(i) == -1){
           map.put("msg","余额不足，请充值!");
           return map;
        }else {
            userCustomer.setBalance(balances.subtract(i));
            userCustomer.setMember(1);
            userCustomerService.update(userCustomer);
            map.put("msg","注册成功");
            return map;
        }
    }

    @RequestMapping("/address")
    public String address(Model model,HttpSession session){
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



    @RequestMapping("/center")
    public String center(Model model,HttpSession session){
        if (session.getAttribute("userCustomer") != null){
            UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
            session.setAttribute("userCustomer",userCustomer);
        }
        return "user_center";
    }

    @RequestMapping("/coupon")
    public String coupon(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_coupon";
    }

    @RequestMapping("/favorites")
    public String favorites(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "favorites";
    }
    @RequestMapping("/message")
    public String message(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_message";
    }
    @RequestMapping("/order")
    public String order(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_order";
    }
    @RequestMapping("/orderlist")
    public String orderlist(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_orderlist";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/";
    }

}
