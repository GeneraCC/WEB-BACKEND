package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

import com.generacc.backend.calidad.backendcalidad.model.calidad.ItemRegistroCalidad;

public interface SupervisorCalidadService {

   boolean insertarRegistros(Long idUsuario,List<ItemRegistroCalidad> registros);
 
    
}
