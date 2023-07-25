package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.List;

import com.generacc.backend.calidad.backendcalidad.model.entity.ParentescoCampana;

public interface ParentescoService {
    List<ParentescoCampana> findByCentroCostoAndActivo(Long centroCosto,Boolean activo);
}
