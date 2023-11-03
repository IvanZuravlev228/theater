package tr11.theater.dto.user;

import lombok.Data;

@Data
public class UserRegisterDto {
    private String email;
    private String password;
    private String firstname;
}
