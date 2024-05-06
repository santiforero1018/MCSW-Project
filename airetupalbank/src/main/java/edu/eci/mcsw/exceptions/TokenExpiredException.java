package edu.eci.mcsw.exceptions;

import static edu.eci.mcsw.utils.Constanst.TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE;

public class TokenExpiredException extends RuntimeException{
    public TokenExpiredException(){
        super(TOKEN_EXPIRED_MALFORMED_ERROR_MESSAGE);
    }
}
