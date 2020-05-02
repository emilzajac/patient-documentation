package com.patient.treatment.documentation.gui.model.dto.mappers.decorators;

import com.patient.treatment.documentation.gui.model.dto.UserDto;
import com.patient.treatment.documentation.gui.model.dto.mappers.UserMapper;
import com.patient.treatment.documentation.gui.model.entites.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


public abstract class UserMapperDecorator implements UserMapper {

    @Autowired
    @Qualifier(value = "delegate")
    UserMapper delegate;

    @Override
    public UserDto toUserDTO(User user) {
        return delegate.toUserDTO(user);
    }

}
