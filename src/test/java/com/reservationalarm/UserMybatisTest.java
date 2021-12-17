package com.reservationalarm;

import com.reservationalarm.user.UserMapper;
import com.reservationalarm.user.domain.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.Matchers.is;

public class UserMybatisTest {
    @Autowired
    UserMapper mapper;

    @Test
    public void findUserById(){
        User user = mapper.selectUserById("123");
        Assert.assertNotNull(user);
    }

    @Test
    public void findAllUsers(){
        List<User> list = mapper.selectAllUsers();
        Assert.assertNotNull(list);
        //System.out.println(list.toString());
    }

//    @Test
//    public void insertUser(){
//        User user = User.builder()
//                .id("12")
//                .password("12")
//                .name("김영빈")
//                .build();
//        mapper.insertUser(user);
//    }
}
