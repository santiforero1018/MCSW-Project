package edu.eci.mcsw.services.user;

import edu.eci.mcsw.Model.userInfo.UserEnt;
import edu.eci.mcsw.exceptions.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserEnt> getUserByName(String name) throws UserNotFoundException;
    Optional<UserEnt> getUserByEmail(String email) throws UserNotFoundException;
    List<UserEnt> allUsers();

}
