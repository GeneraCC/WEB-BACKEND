package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.entity.Perfil;

public interface PerfilRepository extends CrudRepository<Perfil,Long> {
    Optional <Perfil> findByNombrePerfil (String name) ;
}
