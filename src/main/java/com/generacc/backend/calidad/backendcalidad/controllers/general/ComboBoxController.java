package com.generacc.backend.calidad.backendcalidad.controllers.general;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generacc.backend.calidad.backendcalidad.model.dto.LocalidadDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.MotivoRechazo;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.LocalidadImpl;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.MotivoRechazoImpl;

@RestController
@RequestMapping("/generales")
public class ComboBoxController {
    
    @Autowired
    public LocalidadImpl localidad;

    @Autowired
    private MotivoRechazoImpl rechazos;

    @GetMapping("/motivosrechazos")
    public List<MotivoRechazo> getMotivoRechazo() {
        return rechazos.findByActivo();
    }

    @GetMapping("/localidad")
    public List<LocalidadDto> findJoinedData(){
        return localidad.findJoinedData();
    }

}