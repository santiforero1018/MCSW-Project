package edu.eci.mcsw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static edu.eci.mcsw.utils.Constanst.BAD_CREDENTIALS;

public class InvalidCredentialException extends ResponseStatusException {
    public InvalidCredentialException(){
        super(HttpStatus.UNAUTHORIZED, BAD_CREDENTIALS);
    }

    public InvalidCredentialException(String email){
        super(HttpStatus.FORBIDDEN, "Invalid Email: "+email);
    }
}
