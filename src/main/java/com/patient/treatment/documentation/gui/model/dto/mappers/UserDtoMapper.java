package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;

public class UserDtoMapper {

    public static UserDto userProjectonToUserDto(UserProjection userProjection) {
        return UserDto.builder()
                .email(userProjection.getEmail())
                .name(userProjection.getName())
                .username(userProjection.getUsername())
                .password(userProjection.getPassword())
                .surname(userProjection.getSurname())
                .userRoles(userProjection.getUserRoles())
                .build();
    }

    public static User userDtoToUserEntity(UserDto userDto) {
        return User.builder()
                .email(userDto.getEmail())
                .name(userDto.getName())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .surname(userDto.getSurname())
                .userRoles(userDto.getUserRoles())
                .build();
    }

}
