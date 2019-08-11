package com.mall.food;

import com.mall.food.mapper.AdministratorMapper;
import com.mall.food.pojo.Administrator;
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
    private JdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() throws SQLException {
        System.out.println("----"+dataSource.getConnection());
        Integer count = jdbcTemplate.queryForObject("select count(*) from user_customer",Integer.class);
        System.out.println(count);
    }

    @Autowired
    private AdministratorMapper administratorMapper;

    @Test
    public void contextLoads2(){
        List<Administrator> empList = administratorMapper.selectAll();
        System.out.println(empList);
    }



}
