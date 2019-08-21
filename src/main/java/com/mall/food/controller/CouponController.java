package com.mall.food.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.food.mapper.UserCouponMapper;
import com.mall.food.pojo.Coupon;
import com.mall.food.pojo.UserCoupon;
import com.mall.food.pojo.UserCustomer;
import com.mall.food.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/coupon/")
public class CouponController {
    @Autowired
    private CouponService couponService;
    @Autowired
    private UserCouponMapper userCouponMapper;
    @RequestMapping("tianjia")
    @ResponseBody
        public Object couponAdd(Model model, HttpSession session,@RequestParam("coupon") Integer coupon) {
        System.out.println(coupon);
        Map<String, String> map = new HashMap<>();
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        if (userCustomer == null || userCustomer.equals("")) {
//            System.out.println("fafafafasfadfsfadfa");
            map.put("msg","请先登录");
            return map;
        } else {
//            System.out.println("的大V拿到手上");

            Coupon coupon1 = couponService.selectOne(coupon);
            List<UserCoupon> list = userCouponMapper.selectUserCouponById(userCustomer.getUserId());
            for (UserCoupon u : list) {
                    if (u.getCoupon() == coupon1.getCoupon()) {
                        map.put("msg", "已拥有该优惠券，请使用后再添加!");
                        return map;
                    }
            }
            UserCoupon userCoupon=new UserCoupon();
            userCoupon.setCoupon(coupon1.getCoupon());
            userCoupon.setUserId(userCustomer.getUserId());
            userCoupon.setDescribes(coupon1.getDescribes());
            userCoupon.setIcon(coupon1.getIcon());
            userCouponMapper.insertUserCoupon(userCoupon);
            map.put("msg", "添加成功");
            return map;

        }
    }
    @RequestMapping("selectByUserId")
    public String selectByUserId(HttpSession session,Model model){
        UserCustomer userCustomer = (UserCustomer) session.getAttribute("userCustomer");
        List<UserCoupon> list = userCouponMapper.selectUserCouponById(userCustomer.getUserId());
        model.addAttribute("list",list);
        return "user_coupon";
    }
//      //查询单条数据，判断是否为空。对应进行添加或修改
//      @GetMapping("add/{coupon}")
//      public String fingOne(@PathVariable Integer coupon, Model model){
//          Coupon coupon1=couponService.selectOne(coupon);
//          model.addAttribute("coupon",coupon1);
//          return "couponAdd";
//      }
//      //修改方法的调用
//      @PutMapping("add")
//      public  String modify(Coupon coupon){
//         couponService.update(coupon);
//         //修改完重定向到查询列表
//         return "redirect:/coupon/alls";
//      }
//      //进入添加页面
//      @GetMapping("add")
//      public String init(){
//          return "couponAdd";
//      }
//      //实现添加功能后重定向到查询列表
//      @PostMapping("add")
//      public String insert(Coupon coupon){
//          couponService.insert(coupon);
//          return "redirect:/coupon/alls";
//      }
//
//      @DeleteMapping("add/{coupon}")
//      public String delete(@PathVariable Integer coupon){
//          couponService.delete(coupon);
//          return "redirect:/coupon/alls";
//      }

}
