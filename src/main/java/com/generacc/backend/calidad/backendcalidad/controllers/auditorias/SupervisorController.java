package com.generacc.backend.calidad.backendcalidad.controllers.auditorias;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generacc.backend.calidad.backendcalidad.model.dto.UsuarioDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.Usuario;
import com.generacc.backend.calidad.backendcalidad.services.UserServiceImpl;
import com.generacc.backend.calidad.backendcalidad.services.calidadServices.SupervisorCalidadServiceImpl;



@RestController
@RequestMapping("/supervisorcalidad")
public class SupervisorController {

    @Autowired
    private SupervisorCalidadServiceImpl service;

    @Autowired
    private UserServiceImpl servicioUsuarioImpl;

    @GetMapping("/listarEjecutivos/{idperfil}")
    public List<UsuarioDto> getUsersWithPerfil(@PathVariable Long idperfil) {
        return servicioUsuarioImpl.getUsersWithPerfil(idperfil);
    }

    
    

    
}
