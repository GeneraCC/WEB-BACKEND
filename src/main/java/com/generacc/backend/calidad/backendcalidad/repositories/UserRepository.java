package com.generacc.backend.calidad.backendcalidad.repositories;

import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;

public interface UserRepository extends CrudRepository<Usuario, Long> {

}
