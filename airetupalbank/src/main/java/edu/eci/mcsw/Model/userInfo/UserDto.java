package edu.eci.mcsw.Model.userInfo;

import edu.eci.mcsw.Model.enums.UserRoles;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Data
public class UserDto {
    private String username;
    private String email;
    private String password;
    private String role;

    public UserDto() {
    }

    public UserDto(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserDto(String username, String email){
        this.username = username;
        this.email = email;
    }

}