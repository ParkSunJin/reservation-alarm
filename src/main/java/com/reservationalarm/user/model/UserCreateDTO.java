package com.reservationalarm.user.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDTO {
    private String id;
    private String password;
    private String name;
}
