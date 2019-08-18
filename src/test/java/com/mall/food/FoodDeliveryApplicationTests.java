package com.mall.food;

import com.mall.food.mapper.*;
import com.mall.food.pojo.*;
import org.apache.ibatis.annotations.Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
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
    private UserCustomerMapper businessMapper;

    @Test
    public void contextLoads4(){
        UserCustomer empList = businessMapper.selectUserCustomerByUserName("花花");
        System.out.println(empList);
    }

    @Test
    public void contextLoads5(){
        BigDecimal bigDecimal = BigDecimal.valueOf(0.00);
        UserCustomer userCustomer = UserCustomer.builder().userId("123").userName("123").tel("123").password("123456").userKey("123456").balance(bigDecimal).member(1).build();
        System.out.println(userCustomer);
        businessMapper.insertUserCustomer(userCustomer);
        List<UserCustomer> empList = businessMapper.selectAll();
        System.out.println(empList);
    }

}
