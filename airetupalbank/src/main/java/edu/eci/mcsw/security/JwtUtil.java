package edu.eci.mcsw.security;

import edu.eci.mcsw.Model.enums.UserRoles;
import edu.eci.mcsw.Model.security.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static edu.eci.mcsw.utils.Constanst.CLAIMS_ROLES_KEY;

/**
 * Class to generate and verify tokens
 */
@Component
public class JwtUtil {

    private final JwtConfig jwtConfig;

    /**
     * Default constructor
     * @param jwtConfig
     */
    public  JwtUtil(JwtConfig jwtConfig){ this.jwtConfig = jwtConfig;}

    /**
     * method to generate a new token
     * @param username
     * @param roles
     * @return a TokenDto object
     */
    public TokenDto generateToken(String username, List<UserRoles> roles) {

        Date expirationDate = jwtConfig.getExpirationDate();
        String token = Jwts.builder().subject(username)
                .issuedAt(new Date())
                .expiration(expirationDate)
                .claim(CLAIMS_ROLES_KEY, roles)
                .signWith(jwtConfig.getSigningKey())
                .compact();
        return new TokenDto(token, expirationDate);
    }

    /**
     * method that verify a token
     * @param token
     * @return A Claim
     */
    public Claims extractAndVerifyClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtConfig.getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
