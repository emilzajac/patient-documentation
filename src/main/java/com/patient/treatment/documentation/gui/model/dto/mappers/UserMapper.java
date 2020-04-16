package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.form.UserRegisterForm;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto toUserDTO(UserProjection userProjection);

    User toUserEntity(UserProjection userProjection);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "patients", ignore = true)
    User toUserEntity(UserDto userDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userRoles", ignore = true)
    @Mapping(target = "patients", ignore = true)
    User toUserEntity(UserRegisterForm userRegisterForm);

}
