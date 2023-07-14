package com.generacc.backend.calidad.backendcalidad.auth;

import java.util.Arrays;
import org.springframework.web.filter.CorsFilter;
import com.generacc.backend.calidad.backendcalidad.auth.filters.JwtAuthenticationFilter;
import com.generacc.backend.calidad.backendcalidad.auth.filters.JwtValidationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();}
    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/supervisorcalidad/**").hasRole("Supervisor_Calidad")
                        .requestMatchers("/ejecutivocalidad/**").hasRole("Ejecutivo_Calidad")
                        .requestMatchers("/calidad/**").hasAnyRole("Supervisor_Calidad","Ejecutivo_Calidad")
                        .anyRequest().authenticated())
                .addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        
        return http.build();
    }
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:3000",
                                                    "http://localhost:3001",
                                                    "http://localhost:3002",
                                                    "http://localhost:3003",
                                                    "http://localhost:3004",
                                                    "http://localhost:3005",
                                                    "http://localhost:3006"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;


    }
    

}