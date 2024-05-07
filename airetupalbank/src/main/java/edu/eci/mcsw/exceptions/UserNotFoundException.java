package edu.eci.mcsw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserNotFoundException extends ResponseStatusException {
    public UserNotFoundException(String username) {
        super(HttpStatus.NOT_FOUND, "user with this username:"+username+" not found");
    }

    public UserNotFoundException(){
        super(HttpStatus.NOT_FOUND, "user not found");
    }
}
