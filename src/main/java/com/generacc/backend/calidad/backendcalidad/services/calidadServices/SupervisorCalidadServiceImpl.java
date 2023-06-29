package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;

@Service
public class SupervisorCalidadServiceImpl implements SupervisorCalidadService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Boolean insertarRegistros(Long idUsuario, List<Map<Long,Integer>> registros) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertarRegistros'");
    }
    

    
}
