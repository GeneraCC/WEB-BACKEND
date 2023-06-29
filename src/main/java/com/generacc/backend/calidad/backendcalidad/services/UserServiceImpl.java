package com.generacc.backend.calidad.backendcalidad.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generacc.backend.calidad.backendcalidad.model.dto.UsuarioDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.Perfil;
import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;
import com.generacc.backend.calidad.backendcalidad.repositories.PerfilRepository;
import com.generacc.backend.calidad.backendcalidad.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        Optional<Perfil> o = perfilRepository.findByNombrePerfil("Supervisor Calidad");
        List<Perfil> perfil= new ArrayList<Perfil>();
        if(o.isPresent()){
            perfil.add(o.orElseThrow());
        }
        usuario.setPerfil(perfil);
        return repository.save(usuario);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }
    
    @Override
    @Transactional
    public List<UsuarioDto> getUsersWithPerfil(Long idperfil) {
        return repository.findUsersByPerfilId(idperfil);
    }

}
