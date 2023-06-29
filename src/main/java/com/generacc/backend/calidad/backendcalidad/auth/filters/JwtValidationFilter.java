package com.generacc.backend.calidad.backendcalidad.auth.filters;

import static com.generacc.backend.calidad.backendcalidad.auth.TokenJwtConfig.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generacc.backend.calidad.backendcalidad.auth.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                String header = request.getHeader(Header_AUTHORIZATION);
                if(header==null || !header.startsWith("Bearer ")){
                    chain.doFilter(request, response);
                    return;
                }
                String token = header.replace(Prefix_Token, "");
                
                try{
                    Claims claims=Jwts.parserBuilder().setSigningKey(Secret_Key).build().parseClaimsJws(token).getBody();
                    String username = claims.getSubject();
                    Object authoritiesClaims = claims.get("authorities");
                    Collection<? extends GrantedAuthority> authorities = Arrays.asList(new ObjectMapper()
                    .addMixIn(SimpleGrantedAuthority.class,SimpleGrantedAuthorityJsonCreator.class)
                    .readValue(authoritiesClaims.toString().getBytes(),SimpleGrantedAuthority[].class)); 
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    chain.doFilter(request, response);
                }catch(JwtException e){
                    Map<String,String> body = new HashMap<String,String>();
                    body.put("error",e.getMessage());
                    response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                    response.setStatus(403);
                    response.setContentType("application/json");

                }           
    }
}
