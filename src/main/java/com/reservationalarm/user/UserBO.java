package com.reservationalarm.user;

import com.reservationalarm.user.domain.User;
import com.reservationalarm.user.model.UserCreateDTO;
import com.reservationalarm.user.model.UserReadDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBO {
    @Autowired
    UserMapper mapper;
    public List<User> findAllUser(){
        return mapper.selectAllUsers();
    }

    //==========================옛날 코드================
    private final UserDAO userDAO;

    public String createUser(UserCreateDTO dto) {

        // 회원가입과 관련된 비즈니스 로직 추가
        var userEntity = UserModelConverter.of(dto);
        return userDAO.createUser(userEntity);
    }

    public UserReadDTO readUser(String id) {
        var userEntity = userDAO.readUser(id);
        return UserModelConverter.of(userEntity);

    }
}