package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.generacc.backend.calidad.backendcalidad.model.entity.MotivoRechazo;

public interface MotivoRechazoRepository extends CrudRepository<MotivoRechazo, Long>{
    List<MotivoRechazo> findAll();
}
