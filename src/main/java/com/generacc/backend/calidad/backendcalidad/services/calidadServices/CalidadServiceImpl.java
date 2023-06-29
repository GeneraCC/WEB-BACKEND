package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.List;
import java.util.Map;

import javax.swing.text.rtf.RTFEditorKit;

import org.hibernate.query.spi.QueryEngine;
import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.generacc.backend.calidad.backendcalidad.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class CalidadServiceImpl implements CalidadService {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Page<Map<String, Object>> getVentasPorAuditar(Long id, int numeroPagina, int tamano) {
        try {
            String querysql = "EXEC CALIDAD_WEB.dbo.pa_ListaVentasAEvaluar";
            Query query;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Ejecutivo_Calidad"))){
                querysql = "EXEC CALIDAD_WEB.dbo.pa_ListaVentasAEvaluarAsignadas :idusuario";
                query = entityManager.createNativeQuery(querysql);
                query.setParameter("idusuario", id);
            }else{
                query = entityManager.createNativeQuery(querysql);
            }
            var nativeQuery = (NativeQueryImpl) query;
            nativeQuery.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);
            List<Map<String, Object>> resultList = nativeQuery.getResultList();
            int totalElements = resultList.size();
            int comienzo = Math.max(0, (numeroPagina - 1) * tamano);
            int finalLista = Math.min(comienzo + tamano, totalElements);
            List<Map<String, Object>> subList = resultList.subList(comienzo, finalLista);
            return new PageImpl<>(subList, PageRequest.of(numeroPagina - 1, tamano), totalElements);
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        
        }
        
     
    
}}
