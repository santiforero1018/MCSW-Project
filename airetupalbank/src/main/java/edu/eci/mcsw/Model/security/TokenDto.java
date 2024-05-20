package edu.eci.mcsw.Model.security;

import lombok.Data;

import java.util.Date;

@Data
public class TokenDto {
    private String token;
    private Date expDate;

    /**
     * Default constructor
     * @param token
     * @param expDate
     */
    public TokenDto(String token, Date expDate){
        this.token = token;
        this.expDate = expDate;
    }
}
