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

    @RequestMapping("/read")
    public String read(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        if (userCustomer != null){
            session.setAttribute("userCustomer",userCustomer);
        }
        return "article_read";
    }

    @RequestMapping("/category")
    public String category(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "category";
    }

//用户中心
    @RequestMapping("/center")
    public String center(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_center";
    }

    //优惠劵
    @RequestMapping("/coupon")
    public String coupon(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "redirect:/coupon/selectByUserId";
    }
//我的收藏
    @RequestMapping("/favorites")
    public String favorites(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "favorites";
    }
//    客服
    @RequestMapping("/message")
    public String message(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        return "user_message";
    }


    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "/";
    }

}
