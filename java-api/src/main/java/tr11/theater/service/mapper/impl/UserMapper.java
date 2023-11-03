package tr11.theater.service.mapper.impl;

import org.springframework.stereotype.Component;
import tr11.theater.dto.user.UserRegisterDto;
import tr11.theater.dto.user.UserResponseDto;
import tr11.theater.model.User;
import tr11.theater.service.mapper.RequestResponseMapper;

@Component
public class UserMapper implements RequestResponseMapper<User, UserRegisterDto, UserResponseDto> {
    @Override
    public User toModel(UserRegisterDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstname(dto.getFirstname());
        return user;
    }

    @Override
    public UserResponseDto toDto(User model) {
        UserResponseDto dto = new UserResponseDto();
        dto.setEmail(model.getEmail());
        dto.setFirstname(model.getFirstname());
        dto.setRole(model.getRole().name());
        dto.setId(model.getId());
        return dto;
    }
}
