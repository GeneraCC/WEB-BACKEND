package com.generacc.backend.calidad.backendcalidad.services;

import java.util.List;
import java.util.Optional;

import com.generacc.backend.calidad.backendcalidad.model.dto.UsuarioDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;

public interface UserService {

    List<Usuario> findAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario usuario);

    void remove(Long id);

    List<UsuarioDto> getUsersWithPerfil(Long idperfil);
}
