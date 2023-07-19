package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.generacc.backend.calidad.backendcalidad.model.entity.Comuna;

public interface ComunaRepository extends CrudRepository<Comuna,Long>{
    List<Comuna> findByActivo(Boolean activo);
}
