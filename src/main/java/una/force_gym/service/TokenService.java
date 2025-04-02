package una.force_gym.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {
    
    @Value("${app.token.secret}")
    private String tokenSecret;
    
    private Algorithm getAlgorithm() {
        return Algorithm.HMAC512(tokenSecret);
    }
    
    public String generateToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000L))
                .sign(getAlgorithm());
    }
    
    public String getEmailFromToken(String token) {
        try {
            DecodedJWT jwt = getVerifier().verify(token);
            return jwt.getSubject();
        } catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token", e);
        }
    }
    
    public boolean validateToken(String token) {
        try {
            getVerifier().verify(token);
            return true;
        } catch (JWTVerificationException e) {
            return false;
        }
    }
    
    private JWTVerifier getVerifier() {
        return JWT.require(getAlgorithm())
                .build();
    }
}