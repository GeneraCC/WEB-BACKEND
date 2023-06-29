package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

public interface SupervisorCalidadService {

   Boolean insertarRegistros(Long idUsuario,List<Map<Long,Integer>> registros);
 
    
}
