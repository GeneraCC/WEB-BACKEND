package com.generacc.backend.calidad.backendcalidad.controllers.general;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    public List<ParentescoCampana> findParentescoCampanas(@PathVariable Long centroCosto){
        List<ParentescoCampana> tipoPersonas = parentesco.findByCentroCostoAndActivo(centroCosto,true);
        Set<String> nombresUnicos = new HashSet<>();
        for (ParentescoCampana objeto : tipoPersonas) {
            nombresUnicos.add(objeto.getTipoPersona().getTipoPersona());
        }

        List<ParentescoCampana> tipoPersonaDTOs = new ArrayList<>();
        // String algo = tipoPersonas.getTipoPersona().toString();
        for (ParentescoCampana tipoPersona : tipoPersonas) {
            TipoPersonaDto tipoPersonaDTO = new TipoPersonaDto();
            String algso = tipoPersona.getTipoPersona().getTipoPersona();
        }
        return parentesco.findByCentroCostoAndActivo(centroCosto,true);
    }
}