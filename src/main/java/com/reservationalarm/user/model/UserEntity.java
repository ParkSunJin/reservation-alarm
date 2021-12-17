package com.reservationalarm.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserEntity {
    private Long userSeq;
    private String id;
    private String password;
    private String name;
}
