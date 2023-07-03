package com.generacc.backend.calidad.backendcalidad.controllers.auditorias;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generacc.backend.calidad.backendcalidad.model.dto.UsuarioDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.Registro;
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

    @PostMapping("/asignarregistros")
    public ResponseEntity<Void> asignarRegistros(@RequestBody Registro registros){
        System.out.println(registros.getIdUsuario());
        System.out.println(registros.getRegistros());
        if(service.insertarRegistros(registros.getIdUsuario(),registros.getRegistros())){
            return ResponseEntity.ok().build();

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
         

    }

    
    

    
}
