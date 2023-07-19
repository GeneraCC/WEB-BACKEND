package com.generacc.backend.calidad.backendcalidad.repositories;

import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.entity.Ciudad;
import java.util.List;


public interface CiudadRepository extends CrudRepository<Ciudad,Long>{
    List<Ciudad> findAll();
}
