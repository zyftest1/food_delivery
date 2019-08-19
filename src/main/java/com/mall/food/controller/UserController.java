package com.mall.food.controller;

import com.mall.food.pojo.UserAddress;
import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.UserAddressService;
import com.mall.food.service.UserCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

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

    @RequestMapping("/register1")
    public String confirmUserName(Model model,String userName,String password,String password2,String email,String tel,String captcha){
        List<UserCustomer> userCustomerList = userCustomerService.getAll();
        for (UserCustomer userCustomer : userCustomerList) {
            if (userCustomer.getUserName().equals(userName)) {
                model.addAttribute("msg", "用户名被占用");
                return "register";
            }
        }
        if (!password.equals(password2)) {
            model.addAttribute("msg2", "密码不相等,请重新输入");
            return "register";
        }

        UserCustomer userCustomer = UserCustomer.builder().userId(tel).userName(userName).password(password).email(email).tel(tel).userKey("123456").balance(new BigDecimal(0.00)).member(0).build();
        userCustomerService.insert(userCustomer);
        //        model.addAttribute("msg1","用户名可用");
        return "login";
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

    @RequestMapping("/address")
    public String address(Model model,HttpSession session){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        session.setAttribute("userCustomer",userCustomer);
        List<UserAddress> userAddressList = userAddressService.getAll(userCustomer.getUserId());
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
        return "initIndex";
    }

}
