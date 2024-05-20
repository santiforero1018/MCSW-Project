package edu.eci.mcsw.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BillNotFoundException extends ResponseStatusException {
    public BillNotFoundException(String reference){
        super(HttpStatus.NOT_FOUND, "Bill with this reference: "+ reference +" Not found");
    }
}
