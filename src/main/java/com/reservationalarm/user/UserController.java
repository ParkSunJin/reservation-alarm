package com.reservationalarm.user;

import com.reservationalarm.user.domain.User;
import com.reservationalarm.user.model.UserCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserBO userBO;

    @GetMapping("/user")
    public ResponseEntity<List<User>> findAllUser() throws IOException {
        List<User> list = userBO.findAllUser();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping("/user")
    public ResponseEntity<Integer> insertUser(@RequestBody UserCreateDTO userCreateDTO){
        userBO.insertUser(userCreateDTO);
        return ResponseEntity.ok().body(200);
    }
}
