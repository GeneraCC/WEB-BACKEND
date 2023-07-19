package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import com.generacc.backend.calidad.backendcalidad.model.dto.LocalidadDto;

public interface LocalidadService {
    
    List<LocalidadDto> findJoinedData();
}