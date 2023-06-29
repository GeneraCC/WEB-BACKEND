package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;
import com.generacc.backend.calidad.backendcalidad.repositories.UserRepository;



@Service
public class EjecutivoCalidadServiceImpl implements EjecutivoCalidadService {

    @Autowired
    private UserRepository userRepository;

    public Long findUserIdByUsername(String username){
        Optional<Usuario> user = userRepository.findBynombreusuario(username);
        if(user != null){
            return user.get().getIdusuario();
        }
        return null;

    }
    
    
       
}
