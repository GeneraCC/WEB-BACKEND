package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface CalidadService {
    Page<Map<String,Object>> getVentasPorAuditar(Long id,int numeroPagina,int tamano);
    Map<String, Object> detalleRegistro(int idRegistro,Authentication authentication);
    List<Map<String, Object>> resumenEjecutvioCalidad(Long id);
    Boolean actualizarRegistro (String request);


    
    
}
