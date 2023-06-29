package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class SupervisorCalidadServiceImpl implements SupervisorCalidadService {

    @Autowired
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<Map<String, Object>> getVentasPorAuditar(int numeroPagina,int tamano) {
        try {
            Query query = entityManager.createNativeQuery("EXEC CALIDAD_WEB.dbo.pa_ListaVentasAEvaluar");
            var nativeQuery = (NativeQueryImpl) query;
            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> resultList = nativeQuery.getResultList();
            int totalElements = resultList.size();
            int totalPages = (int) Math.ceil((double) totalElements / tamano);
            int comienzo = Math.max(0, (numeroPagina - 1) * tamano);
            int finalLista = Math.min(comienzo + tamano, totalElements);
            List<Map<String, Object>> subList = resultList.subList(comienzo, finalLista);
            return new PageImpl<>(subList, PageRequest.of(numeroPagina - 1, tamano), totalElements);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    

    
}
