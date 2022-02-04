package com.reservationalarm.user;

import com.reservationalarm.user.domain.User;
import com.reservationalarm.user.model.UserCreateDTO;
import com.reservationalarm.user.model.UserEntity;
import com.reservationalarm.user.model.UserReadDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * CreateDTO -> entity (DB)
 * ReadDTO <- entity (DB)
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserModelConverter {
    public static User of(UserCreateDTO dto) {
        return User
                .builder()
                .userId(dto.getUserId())
                .userPassword(dto.getUserPassword())
                .userName(dto.getUserName())
                .build();
    }

    public static UserReadDTO of(User user) {
        return UserReadDTO.builder()
                .userName(user.getUserName())
                .build();
    }
}
