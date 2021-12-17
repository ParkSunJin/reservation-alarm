package com.reservationalarm.user.domain;

import lombok.*;

@Getter
@ToString
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String id;
    public String password;
    public String name;
}
