package edu.eci.mcsw.controllers;

import edu.eci.mcsw.Model.security.LoginDto;
import edu.eci.mcsw.Model.security.TokenDto;
import edu.eci.mcsw.Model.userInfo.UserDto;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.InvalidCredentialException;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.security.JwtUtil;
import edu.eci.mcsw.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    private final JwtUtil jwtUtil;
    public LoginController(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginData) throws InvalidCredentialException {
        try{
            UserEnt verify = userService.getUserByEmail(loginData.getEmail());
            if(BCrypt.checkpw(loginData.getPassword(), verify.getPassword())){
                return ResponseEntity.ok(jwtUtil.generateToken(verify.getEmail(), verify.getRoles()));
            }
        } catch (UserNotFoundException e){
            throw new InvalidCredentialException();
        }

        return null;
    }

    @PostMapping("/register")
    public ResponseEntity<?> signUp(@RequestBody UserDto newUSer){
        try{
            this.userService.registerNewUser(new UserEnt(newUSer));
            return ResponseEntity.ok("User Created Correctly");
        } catch (InvalidCredentialException e){
            return new ResponseEntity<>(e.getMessage(),e.getStatusCode());
        }
    }
}
