package edu.eci.mcsw.controllers;

import edu.eci.mcsw.Model.enums.UserRoles;
import edu.eci.mcsw.Model.userInfo.UserDto;
import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.UserNotFoundException;
import edu.eci.mcsw.services.user.UserService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getAnUser(@PathVariable String email, @RequestHeader(value = "Authorization") String authHeader){
        try{
            UserEnt query = this.userService.getUserByEmail(email);
//            UserDto data = new UserDto(query.getName(), query.getEmail());
            return ResponseEntity.ok(query);
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping("/roles")
    public ResponseEntity<?> modifyUserRoles(@RequestBody UserDto user){
        try{
            UserEnt modifyUser = this.userService.getUserByName(user.getEmail());
            if ((user.getRole().equals("ADMIN"))) {
                modifyUser.addRole(UserRoles.ADMIN);
            } else {
                modifyUser.addRole(UserRoles.AUDIT);
            }
            this.userService.updateUser(modifyUser);

            return ResponseEntity.ok("User modified Correctly");
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @PutMapping("/deniedRoles")
    public ResponseEntity<?> deleteUserRoles(@RequestBody UserDto user){
        try{
            UserEnt modifyUser = this.userService.getUserByName(user.getEmail());
            if ((user.getRole().equals("ADMIN"))) {
                modifyUser.deleteRole(UserRoles.ADMIN);
            } else {
                modifyUser.deleteRole(UserRoles.AUDIT);
            }
            this.userService.updateUser(modifyUser);

            return ResponseEntity.ok("User modified Correctly");
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }

    @RolesAllowed(ADMIN_ROLE)
    @DeleteMapping("/{email}")
    public ResponseEntity<?> deleteAnUser(@PathVariable String email){
        try{
            this.userService.deleteUser(email);
            return ResponseEntity.ok("User Deleted Correctly");
        } catch (UserNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), e.getStatusCode());
        }
    }


}
