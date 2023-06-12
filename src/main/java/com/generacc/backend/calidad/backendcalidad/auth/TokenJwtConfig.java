package com.generacc.backend.calidad.backendcalidad.auth;

import java.security.Key;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenJwtConfig {
    public final static Key Secret_Key=Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public final static String Prefix_Token="Bearer ";
    public final static String Header_AUTHERIZATION="Autherization";
    
}
