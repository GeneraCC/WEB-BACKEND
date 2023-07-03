package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Service
public class CalidadServiceImpl implements CalidadService {

    @Autowired
    private EntityManager entityManager;

    @Override
        public Page<Map<String, Object>> getVentasPorAuditar(Long id, int numeroPagina, int tamano) {
            try {
                String querysql = "EXEC CALIDAD_WEB.dbo.pa_ListaVentasAEvaluar";
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                Query query = this.entityManager.createNativeQuery(querysql, Tuple.class);
                if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Ejecutivo_Calidad"))) {
                    querysql = "EXEC CALIDAD_WEB.dbo.pa_ListaVentasAEvaluarAsignadas :idusuario";
                    query = this.entityManager.createNativeQuery(querysql, Tuple.class);
                    query.setParameter("idusuario", id);
                }
                List<Tuple> tuples = query.getResultList();
                List<Map<String, Object>> resultList = new ArrayList<>();
                for (Tuple tuple : tuples) {
                    Map<String, Object> map = new HashMap<>();
                    for (TupleElement<?> element : tuple.getElements()) {
                        map.put(element.getAlias(), tuple.get(element.getAlias()));
                    }
                    resultList.add(map);
                }
                int totalElements = resultList.size();
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
