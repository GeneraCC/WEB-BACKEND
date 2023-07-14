package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;

import java.util.concurrent.atomic.AtomicBoolean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generacc.backend.calidad.backendcalidad.model.calidad.ItemRegistroCalidad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.transaction.Transactional;


@Service
public class SupervisorCalidadServiceImpl implements SupervisorCalidadService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    @Transactional
    public boolean insertarRegistros(Long idUsuario, List<ItemRegistroCalidad> registros) {
        AtomicBoolean result = new AtomicBoolean(true);
        try {
            String registrosJson = objectMapper.writeValueAsString(registros);
            System.out.println(registrosJson);        
            String querysql = "EXEC [CALIDAD_WEB].[dbo].[pa_InsertarRegistro] :idUsuario, :tablaJson";
            Query query = this.entityManager.createNativeQuery(querysql, Tuple.class);
            query.setParameter("idUsuario",idUsuario);
            query.setParameter("tablaJson",registrosJson);
            query.executeUpdate();
            result.set(true);
        } catch (Exception e) {
            e.printStackTrace();
            result.set(false);
        }
        return result.get();
        }
        
}
    

