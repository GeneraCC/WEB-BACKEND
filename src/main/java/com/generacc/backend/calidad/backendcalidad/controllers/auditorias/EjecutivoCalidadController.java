package com.generacc.backend.calidad.backendcalidad.controllers.auditorias;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generacc.backend.calidad.backendcalidad.services.calidadServices.EjecutivoCalidadServiceImpl;

@RestController
@RequestMapping("/ejecutivocalidad")
public class EjecutivoCalidadController {
    @Autowired
    private EjecutivoCalidadServiceImpl service;
    
}
