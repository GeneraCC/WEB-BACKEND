package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.dto.UsuarioDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;

public interface UserRepository extends CrudRepository<Usuario, Long> {
    
    Optional<Usuario> findBynombreusuario(String nombreUsuario);

    @Query("select u from Usuario u where u.nombreusuario=?1")
    Optional<Usuario> getUserBynombreUsuario(String nombreUsuario);

    @Query("SELECT new com.generacc.backend.calidad.backendcalidad.model.dto.UsuarioDto(u.idusuario, u.nombreusuario) FROM Usuario u JOIN u.perfil p WHERE p.idPerfil = ?1")
    List<UsuarioDto> findUsersByPerfilId(Long idperfil);

    

}
