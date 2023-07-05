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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.generacc.backend.calidad.backendcalidad.repositories.CalidadStoreProcedure;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Service
public class CalidadServiceImpl implements CalidadService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    public CalidadServiceImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate=jdbcTemplate;
    }

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

    @Override
    public Map<String, Object> detalleRegistro(int idRegistro) {
        String querySql = "[Calidad_WEB].[dbo].[pa_DetalleRegistroAEvaluar_Poliza]";
        String nombreParametro = "idRegistro";
        CalidadStoreProcedure calidadStoreProcedure = new CalidadStoreProcedure(jdbcTemplate,querySql,nombreParametro);
        Map<String, Object> parametroProcedimientos = new HashMap<>();
        parametroProcedimientos.put(nombreParametro, idRegistro);
        List<Map<String, Object>> resultado = calidadStoreProcedure.ejecutar(parametroProcedimientos);
        Map<String , Object> resultadoFinal = new HashMap<>();
        resultadoFinal.put("Adicional",resultado);
        resultadoFinal.put("Beneficiarios",resultado);
        resultadoFinal.put("Polizas", resultado);
        return resultadoFinal;
    }

    @Override                           
    public List<Map<String, Object>> resumenEjecutvioCalidad(Long id) {
        String querySql = "[Calidad_WEB].[dbo].[pa_resumenEjecutivo]";
        String nombreParametro = "idUsuario";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CalidadStoreProcedure calidadStoreProcedure = new CalidadStoreProcedure(jdbcTemplate,querySql,nombreParametro);
        Map<String, Object> parametroProcedimientos = new HashMap<>();
        parametroProcedimientos.put(nombreParametro, 0);
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Ejecutivo_Calidad"))) {
            parametroProcedimientos.put(nombreParametro, id);    
        }
        List<Map<String, Object>> resultado = calidadStoreProcedure.ejecutar(parametroProcedimientos);
        return resultado;
        
    }
}
