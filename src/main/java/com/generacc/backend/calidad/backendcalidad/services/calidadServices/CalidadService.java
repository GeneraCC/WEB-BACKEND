package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.Map;

import org.springframework.data.domain.Page;

public interface CalidadService {
    Page<Map<String,Object>> getVentasPorAuditar(Long id,int numeroPagina,int tamano);

    
    
}
