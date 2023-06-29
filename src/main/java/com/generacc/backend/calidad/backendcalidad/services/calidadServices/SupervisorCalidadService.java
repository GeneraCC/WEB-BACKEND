package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;



public interface SupervisorCalidadService {

   Page<Map<String,Object>> getVentasPorAuditar(int numeroPagina,int tamñano);
 
    
}
