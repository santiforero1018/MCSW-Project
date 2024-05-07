package edu.eci.mcsw.services.user;

import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.InvalidCredentialException;
import edu.eci.mcsw.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    void registerNewUser(UserEnt newUser) throws InvalidCredentialException;
    UserEnt getUserByName(String name) throws UserNotFoundException;
    UserEnt getUserByEmail(String email) throws UserNotFoundException;
    List<UserEnt> allUsers();
    void deleteUser(String email) throws UserNotFoundException;
    void updateUser(UserEnt userData) throws UserNotFoundException;

}
