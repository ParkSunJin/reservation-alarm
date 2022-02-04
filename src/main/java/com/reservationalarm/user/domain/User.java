package com.reservationalarm.user.domain;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String userId;
    public String userPassword;
    public String userName;
}
