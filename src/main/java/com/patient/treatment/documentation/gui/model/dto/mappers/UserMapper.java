package com.patient.treatment.documentation.gui.model.dto.mappers;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.decorators.UserMapperDecorator;
import com.patient.treatment.documentation.gui.model.entites.User;
import com.patient.treatment.documentation.gui.model.form.UserRegisterForm;
import com.patient.treatment.documentation.gui.model.projections.UserProjection;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
@DecoratedWith(UserMapperDecorator.class)
public interface UserMapper {

    User toUserEntity(UserRegisterForm userRegisterForm);

    User toUserEntity(UserProjection userProjection);

    @Mapping(target = "userRoles",
            expression = "java( user.getUserRoles().stream().map(UserRole::getRoleEnum).collect(java.util.stream.Collectors.toSet()) )")
    UserDto toUserDTO(User user);

}
