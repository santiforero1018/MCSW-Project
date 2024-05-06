package edu.eci.mcsw.security;


import java.util.Calendar;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfig {
    private String secret;
    private Long expiration;

    public Date getExpirationDate() {
        long expirationTimeInMilliseconds = Calendar.getInstance().getTimeInMillis() + expiration;
        return new Date(expirationTimeInMilliseconds);
    }

    public SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
