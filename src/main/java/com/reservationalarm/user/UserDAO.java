package com.reservationalarm.user;

import com.reservationalarm.user.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDAO {
    User selectUserById(String id);
    List<User> selectAllUsers();
    void insertUser(User user);
}
