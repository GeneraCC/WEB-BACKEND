package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import com.generacc.backend.calidad.backendcalidad.model.entity.Plan;

public interface PlanService {
    List<Plan> findByCentroCosto(Long centroCosto);
}
