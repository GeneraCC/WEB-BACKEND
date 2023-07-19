package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;
import com.generacc.backend.calidad.backendcalidad.model.entity.MotivoRechazo;

public interface MotivoRechazoService {
    List<MotivoRechazo> findAll();
}