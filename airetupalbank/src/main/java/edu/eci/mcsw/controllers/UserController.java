package edu.eci.mcsw.controllers;

import edu.eci.mcsw.Model.userInfo.UserDto;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.services.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static edu.eci.mcsw.utils.Constanst.ADMIN_ROLE;
import static edu.eci.mcsw.utils.Constanst.AUDIT_ROLE;
import static edu.eci.mcsw.utils.Constanst.USER_ROLE;


@RestController
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RolesAllowed({ADMIN_ROLE, AUDIT_ROLE})
    @GetMapping
    public ResponseEntity<List<UserEnt>> getAllUsers(){
        return ResponseEntity.ok(this.userService.allUsers());
    }

    @RolesAllowed({ADMIN_ROLE, USER_ROLE})
    @GetMapping("/{email}")
    public ResponseEntity<?> getAnUser(@PathVariable String email){
        try{
            UserEnt query = this.userService.getUserByEmail(email);
            UserDto data = new UserDto(query.getName(), query.getEmail(), null);
            return ResponseEntity.ok(data);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }
}
