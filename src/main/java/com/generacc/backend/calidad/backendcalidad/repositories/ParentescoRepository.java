package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.entity.ParentescoCampana;

public interface ParentescoRepository extends CrudRepository<ParentescoCampana,Long>{
    List<ParentescoCampana> findByCentroCostoAndActivo(Long centroCosto,Boolean activo);
}
