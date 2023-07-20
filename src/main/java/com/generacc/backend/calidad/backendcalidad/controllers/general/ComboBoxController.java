package com.generacc.backend.calidad.backendcalidad.controllers.general;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generacc.backend.calidad.backendcalidad.model.dto.LocalidadDto;
import com.generacc.backend.calidad.backendcalidad.model.dto.TipoPersonaDto;
import com.generacc.backend.calidad.backendcalidad.model.entity.MotivoRechazo;
import com.generacc.backend.calidad.backendcalidad.model.entity.ParentescoCampana;
import com.generacc.backend.calidad.backendcalidad.model.entity.Plan;
import com.generacc.backend.calidad.backendcalidad.model.entity.TipoPersona;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.LocalidadImpl;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.MotivoRechazoImpl;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.ParentescoImpl;
import com.generacc.backend.calidad.backendcalidad.services.generalServices.PlanImpl;

@RestController
@RequestMapping("/generales")
public class ComboBoxController {
    
    @Autowired
    public LocalidadImpl localidad;

    @Autowired
    private MotivoRechazoImpl rechazos;

    @Autowired
    private PlanImpl plan;

    @Autowired
    private ParentescoImpl parentesco;

    @GetMapping("/motivosrechazos")
    public List<MotivoRechazo> getMotivoRechazo() {
        return rechazos.findByActivo();
    }

    @GetMapping("/localidad")
    public List<LocalidadDto> findJoinedData(){
        return localidad.findJoinedData();
    }

    @GetMapping("/planes/{centroCosto}")
    public List<Plan> findByCentroCosto(@PathVariable Long centroCosto){
        return plan.findByCentroCosto(centroCosto);
    }

    @GetMapping("/parentesco/{centroCosto}")
    public List<TipoPersonaDto> findParentescoCampanas(@PathVariable Long centroCosto){
        List<ParentescoCampana> listaParentesco = parentesco.findByCentroCostoAndActivo(centroCosto,true);
        
        List<TipoPersona> listaPersona = listaParentesco.stream()
                .map(x -> x.getTipoPersona())
                .distinct()
                .collect(Collectors.toList());
    
        List<TipoPersonaDto> tipoPersonaDTOs = new ArrayList<>();
        for (TipoPersona persona : listaPersona){
            TipoPersonaDto personaDTO = new TipoPersonaDto();
            List<ParentescoCampana> listaPc = new ArrayList<>();
            for (ParentescoCampana p : listaParentesco) {
                if (persona.getTipoPersona() == p.getTipoPersona().getTipoPersona()){
                    ParentescoCampana pc = new ParentescoCampana(p.getIdParentescoCampana(), p.getCentroCosto(), p.getEdadMin(), p.getEdadMax(), p.getParentesco(), true);
                    personaDTO.setTipoPersona(persona.getTipoPersona());
                    personaDTO.setIdTipoPersona(persona.getIdTipoPersona());
                    listaPc.add(pc);
                } else continue;
                personaDTO.setParentescoCampanas(listaPc);
            }            
            tipoPersonaDTOs.add(personaDTO);
        }
        return tipoPersonaDTOs;
    }
}