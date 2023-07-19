package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.generacc.backend.calidad.backendcalidad.model.entity.Plan;
import com.generacc.backend.calidad.backendcalidad.repositories.PlanRepository;

@Service
public class PlanImpl implements PlanService{
    @Autowired
    public PlanRepository repo;

    // @Override
    @Transactional(readOnly = true)
    public List<Plan> findByCentroCosto(Long centroCosto) {
        return repo.findByCentroCosto(centroCosto);
    }
    
}
