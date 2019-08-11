package com.mall.food;

import com.mall.food.mapper.AdministratorMapper;
import com.mall.food.mapper.CouponMapper;
import com.mall.food.mapper.OrderMapper;
import com.mall.food.mapper.OrderScheduleMapper;
import com.mall.food.mapper.AdvertisementMapper;
import com.mall.food.mapper.BusinessMapper;
import com.mall.food.pojo.Administrator;
import com.mall.food.pojo.Coupon;
import com.mall.food.pojo.Order;
import com.mall.food.pojo.OrderSchedule;
import org.apache.ibatis.annotations.Select;
import com.mall.food.pojo.Advertisement;
import com.mall.food.pojo.Business;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

@MapperScan("com.mall.food.mapper")
@RunWith(SpringRunner.class)
@SpringBootTest
public class FoodDeliveryApplicationTests {

    @Autowired
    private DataSource dataSource;
//    @Autowired
//    private BasicDataSource basicDataSource;

    @Autowired
    private AdministratorMapper administratorMapper;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrderScheduleMapper orderScheduleMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CouponMapper couponMapper;


    @Test
    public void contextLoads() throws SQLException {
        System.out.println("----"+dataSource.getConnection());
        Integer count = jdbcTemplate.queryForObject("select count(*) from user_customer",Integer.class);
        System.out.println(count);
    }

    @Test
    public void contextLoads2(){
//        List<Order> empList = orderMapper.selAllOrders();
//        System.out.println(empList);
        List<Coupon> coupons = couponMapper.selAllCoupons();
        System.out.println(coupons);
    }

    @Autowired
    private AdvertisementMapper advertisementMapper;

    @Test
    public void contextLoads3(){
        List<Advertisement> empList = advertisementMapper.selectAll();
        System.out.println(empList);
    }

    @Autowired
    private BusinessMapper businessMapper;

    @Test
    public void contextLoads4(){
        List<Business> empList = businessMapper.selectAll();
        System.out.println(empList);
    }
}
