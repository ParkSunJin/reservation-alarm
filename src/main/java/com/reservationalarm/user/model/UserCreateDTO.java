package com.reservationalarm.user.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {
    private String userId;
    private String userPassword;
    private String userName;
}
