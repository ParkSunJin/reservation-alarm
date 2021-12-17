package com.reservationalarm.user;

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
    public static UserEntity of(UserCreateDTO dto) {
        return UserEntity
                .builder()
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .build();
    }

    public static UserReadDTO of(UserEntity userEntity) {
        return UserReadDTO.builder()
                .name(userEntity.getName())
                .build();
    }
}
