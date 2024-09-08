package com.example.project_samplecrud.Security;

import org.springframework.stereotype.Component;

@Component
public class Convert {
    public static String bearerTokenToToken(String token){
        if (token.startsWith("Bearer")) {
            token = token.substring(7);
        }
        return token;
    }
}
