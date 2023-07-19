package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import com.generacc.backend.calidad.backendcalidad.model.entity.Comuna;

public interface ComunaService {

    List<Comuna> findByActivo();
}
