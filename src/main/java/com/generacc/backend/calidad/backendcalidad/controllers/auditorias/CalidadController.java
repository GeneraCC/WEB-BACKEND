package com.generacc.backend.calidad.backendcalidad.controllers.auditorias;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generacc.backend.calidad.backendcalidad.services.calidadServices.CalidadServiceImpl;
import com.generacc.backend.calidad.backendcalidad.services.calidadServices.EjecutivoCalidadServiceImpl;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.MotivoRechazoImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/calidad")
public class CalidadController {
    @Autowired
    private CalidadServiceImpl service;

    @Autowired
    private EjecutivoCalidadServiceImpl serviceCalidad;

    @GetMapping("/ventasparaevaluar/{numeropagina}/{tamano}")
    public Page<Map<String,Object>> getVentasPorAuditar(@PathVariable int numeropagina,@PathVariable int tamano) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = serviceCalidad.findUserIdByUsername(username);
        return service.getVentasPorAuditar(userId,numeropagina, tamano);   
    }
    
    @GetMapping("/detalleregistro/{idregistro}")
    public Map<String, Object> detallerRegistro(@PathVariable int idregistro){
        return service.detalleRegistro(idregistro,SecurityContextHolder.getContext().getAuthentication());
    }

    @GetMapping("/resumen")
    public List<Map<String,Object>> getResumen() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Long userId = serviceCalidad.findUserIdByUsername(username);
        return service.resumenEjecutvioCalidad(userId);   
    }
    @PostMapping(value="/actualizaregistro")
    public ResponseEntity<Void> actualizaRegistro(@RequestBody String request ) {
           if(service.actualizarRegistro(request)){
            return ResponseEntity.ok().build();

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping(value="/actualizaAdicional")
    public ResponseEntity<Void> actualizaAdicional(@RequestBody String request ) {
           if(service.actualizarAdicional(request)){
            return ResponseEntity.ok().build();

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PostMapping(value="/actualizabeneficiario")    
    public ResponseEntity<Void> actualizaBeneficiario(@RequestBody String request ) {
           if(service.actualizarBeneficiario(request)){
            return ResponseEntity.ok().build();

        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}
