package tr11.theater.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr11.theater.dto.user.UserLoginDto;
import tr11.theater.dto.user.UserRegisterDto;
import tr11.theater.dto.user.UserResponseDto;
import tr11.theater.model.User;
import tr11.theater.security.jwt.JwtTokenProvider;
import tr11.theater.service.AuthenticationService;
import tr11.theater.service.mapper.impl.UserMapper;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> register(@RequestBody @Valid UserRegisterDto user) {
        return new ResponseEntity<>(userMapper.toDto(
                authenticationService.register(userMapper.toModel(user))), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginDto userLoginDto)
            throws RuntimeException {
        User user = authenticationService.login(
                userLoginDto.getEmail(), userLoginDto.getPassword());
        Set<User.Role> roles = new HashSet<>();
        roles.add(user.getRole());
        String token = jwtTokenProvider.createToken(user.getEmail(), roles);
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        return new ResponseEntity<>(tokenMap, HttpStatus.OK);
    }
}
