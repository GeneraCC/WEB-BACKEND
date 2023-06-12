package com.generacc.backend.calidad.backendcalidad.services;


import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;
import com.generacc.backend.calidad.backendcalidad.repositories.UserRepository;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    
    @Autowired
    private UserRepository repository;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println(username);
        Optional<Usuario> o =repository.findBynombreusuario(username);

        if(!o.isPresent()){
            throw new UsernameNotFoundException("Username no existe");
        }
        Usuario usuario =o.orElseThrow();
        List<GrantedAuthority> authorities = usuario.getPerfil()
                                            .stream()
                                            .map(r-> new SimpleGrantedAuthority(r.getNombrePerfil()))
                                            .collect(Collectors.toList());
        return new User(usuario.getNombreUsuario(),usuario.getPassword(),true,true,true,true,authorities);
    }
    
}
