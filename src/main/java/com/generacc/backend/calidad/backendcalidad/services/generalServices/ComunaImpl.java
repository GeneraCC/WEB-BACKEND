package com.generacc.backend.calidad.backendcalidad.services.generalServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.generacc.backend.calidad.backendcalidad.model.entity.Comuna;
import com.generacc.backend.calidad.backendcalidad.repositories.ComunaRepository;

@Service
public class ComunaImpl implements ComunaService{
    @Autowired
    public ComunaRepository repo;

    @Override
    public List<Comuna> findByActivo() {
        List<Comuna> listaObjetos = repo.findByActivo(true);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        String json = objectMapper.writeValueAsString(listaObjetos);
        try {
            
            // Convertir la lista de objetos a JSON
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }
    
}
