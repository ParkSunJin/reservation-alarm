package com.reservationalarm.user;

import com.reservationalarm.user.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserDAO {

    public String createUser(UserEntity userEntity) {
        return "123";
    }

    public UserEntity readUser(String id) {
        return UserEntity.builder()
                .userSeq(1L)
                .id("123")
                .password("***")
                .name("김동현")
                .build();
    }
}