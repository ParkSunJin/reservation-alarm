package com.reservationalarm.user;

import com.reservationalarm.user.domain.User;
import com.reservationalarm.user.model.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserBO {
    private final UserDAO userDAO;

    public void insertUser(UserCreateDTO userCreateDTO){
        User user = UserModelConverter.of(userCreateDTO);
        userDAO.insertUser(user);
    }

    public List<User> findAllUser(){
        return userDAO.selectAllUsers();
    }

    public User findUserById(String id){
        return userDAO.selectUserById(id);
    }


}