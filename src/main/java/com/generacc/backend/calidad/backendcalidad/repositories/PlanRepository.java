package com.generacc.backend.calidad.backendcalidad.repositories;

import org.springframework.data.repository.CrudRepository;

import com.generacc.backend.calidad.backendcalidad.model.entity.Plan;
import java.util.List;

public interface PlanRepository extends CrudRepository<Plan, Long>{
    
    List<Plan> findByCentroCosto(Long centroCosto); 
}
