package edu.eci.mcsw.Model.userInfo;

import edu.eci.mcsw.Model.enums.UserRoles;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Data
public class UserDto {
    private final String username;
    private final String email;
    private final String password;

    public UserDto() {
        this.username = "";
        this.email = "";
        this.password = "";
    }

    public UserDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public UserDto(String username, String email){
        this.username = username;
        this.email = email;
        this.password = null;
    }
}