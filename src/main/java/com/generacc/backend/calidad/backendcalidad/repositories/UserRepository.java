package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;

public interface UserRepository extends CrudRepository<Usuario, Long> {
    
    Optional<Usuario> findBynombreusuario(String nombreUsuario);

    @Query("select u from Usuario u where u.nombreusuario=?1")
    Optional<Usuario> getUserBynombreUsuario(String nombreUsuario);

    

}
