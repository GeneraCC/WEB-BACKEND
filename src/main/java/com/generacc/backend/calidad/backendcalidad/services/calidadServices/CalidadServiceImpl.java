package com.generacc.backend.calidad.backendcalidad.services.calidadServices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generacc.backend.calidad.backendcalidad.repositories.CalidadStoreProcedure;
import com.generacc.backend.calidad.backendcalidad.repositories.UserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;

@Service
public class CalidadServiceImpl implements CalidadService {

    private final JdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;
    @Autowired
    public UserRepository repository;

    @Autowired
    private EntityManager entityManager;

    public CalidadServiceImpl(JdbcTemplate jdbcTemplate,ObjectMapper objectMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.objectMapper=objectMapper;
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
    public Map<String, Object> detalleRegistro(int idRegistro,Authentication authentication) {
        Optional<Long> idUsuario=repository.findIdusuarioByNombreUsuario(authentication.getName());
        String queryValida = "SELECT idUsuario from calidad_web.dbo.registroscalidad where idregistro ="+idRegistro;
        Long result =jdbcTemplate.queryForObject(queryValida,Long.class);
        if(result.equals(idUsuario.get())||authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Supervisor_Calidad"))){
            Map<String , Object> resultadoFinal = new HashMap<>();
            System.out.println(idUsuario);
            String querySql = "[Calidad_WEB].[dbo].[pa_DetalleRegistroAEvaluar_Poliza]";
            String nombreParametro = "idRegistro";
            String sql = "select centroCosto "+ 
                         "from CALIDAD_WEB.dbo.registrosCalidad "+
                         "where idRegistro =?";
            String centroCosto = jdbcTemplate.queryForObject(sql
                                                ,String.class
                                                ,idRegistro);
            CalidadStoreProcedure calidadStoreProcedure = new CalidadStoreProcedure(jdbcTemplate,querySql,nombreParametro);
            Map<String, Object> parametroProcedimientos = new HashMap<>();
            parametroProcedimientos.put(nombreParametro, idRegistro);
            List<Map<String, Object>> resultado = calidadStoreProcedure.ejecutar(parametroProcedimientos);
            Long idCliente=null;
            List<Map<String,Object>> adicionales=new ArrayList<>();
            List<Map<String,Object>> beneficiario=new ArrayList<>();
            for(Map<String,Object> iterador : resultado){
                System.out.println(iterador);
                System.out.println(iterador.get("idCliente"));
                if(idCliente==(Long) iterador.get("idCliente")){
                    continue;
                }
                idCliente=(Long) iterador.get("idCliente");
                String queryBeneficiarios = "Select * from BDD_"+centroCosto+".Gestion.Beneficiario Where C_ID="+(Long) iterador.get("idCliente");
                String queryAdicionales = "Select * from BDD_"+centroCosto+".Gestion.Adicional Where C_ID="+(Long) iterador.get("idCliente");
                List<Tuple> adicionalesTupla = this.entityManager.createNativeQuery(queryAdicionales,Tuple.class).getResultList();
                for (Tuple tuple : adicionalesTupla) {
                    Map<String, Object> map = new HashMap<>();
                    for (TupleElement<?> element : tuple.getElements()) {
                        map.put(element.getAlias(), tuple.get(element.getAlias()));
                    }
                    adicionales.add(map);
                }
                List<Tuple>beneficiarioTupla = this.entityManager.createNativeQuery(queryBeneficiarios,Tuple.class).getResultList();
                for (Tuple tuple : beneficiarioTupla) {
                    Map<String, Object> map = new HashMap<>();
                    for (TupleElement<?> element : tuple.getElements()) {
                        map.put(element.getAlias(), tuple.get(element.getAlias()));
                    }
                    beneficiario.add(map);
                }
               
            }            
            resultadoFinal.put("Cabecera",resultado);
            resultadoFinal.put("Polizas", resultado);
            resultadoFinal.put("Beneficiario",beneficiario);
            resultadoFinal.put("Adicionales", adicionales);
            return resultadoFinal;}
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Usuario invalido");
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
    @Override
    public Boolean actualizarRegistro(String request) {
        StringBuilder updateQuery = new StringBuilder("UPDATE BDD_");    
        try {
            JsonNode nodo = objectMapper.readTree(request);
            String queryWhere ="";
            String sql = "select centroCosto "+ 
                         "from CALIDAD_WEB.dbo.registrosCalidad "+
                         "where idRegistro =?";
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = nodo.fields();
            String centroCosto = jdbcTemplate.queryForObject(sql
                                                ,String.class
                                                ,nodo.get("idRegistro").asLong());
            updateQuery.append(centroCosto).append(".Gestion.Polizas SET ");
            boolean isFirstField = true;
            while(fieldsIterator.hasNext()){
                Map.Entry<String,JsonNode> fieldEntry = fieldsIterator.next();
                if(fieldEntry.getKey().equals("p_id")){
                    queryWhere = "WHERE p_id = "+fieldEntry.getValue();
                    continue;
                }
                if(!fieldEntry.getKey().equals("idRegistro")){
                    if(!isFirstField){
                        updateQuery.append(",");
                    }else{
                        isFirstField=false;
                    }
                    String fieldName =fieldEntry.getKey();
                    JsonNode fieldValue =  fieldEntry.getValue();
                    String llaveValor = fieldName+"= '"+fieldValue.asText()+"'";
                    updateQuery.append(llaveValor);}
                }
            String updateQueString = updateQuery.toString();
            updateQueString += " "+queryWhere;
            jdbcTemplate.execute(updateQueString);                     
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
    public Boolean actualizarBeneficiario(String request) {
        StringBuilder updateQuery = new StringBuilder("UPDATE BDD_");    
        try {
            JsonNode nodo = objectMapper.readTree(request);
            String queryWhere ="";
            String sql = "select centroCosto "+ 
                         "from CALIDAD_WEB.dbo.registrosCalidad "+
                         "where idRegistro =?";
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = nodo.fields();
            String centroCosto = jdbcTemplate.queryForObject(sql
                                                ,String.class
                                                ,nodo.get("idRegistro").asLong());
            updateQuery.append(centroCosto).append(".Gestion.Beneficiario SET ");
            boolean isFirstField = true;
            while(fieldsIterator.hasNext()){
                Map.Entry<String,JsonNode> fieldEntry = fieldsIterator.next();
                if(fieldEntry.getKey().equals("p_id")){
                    queryWhere = "WHERE p_id = "+fieldEntry.getValue();
                    continue;
                }
                if(!fieldEntry.getKey().equals("idRegistro")){
                    if(!isFirstField){
                        updateQuery.append(",");
                    }else{
                        isFirstField=false;
                    }
                    String fieldName =fieldEntry.getKey();
                    JsonNode fieldValue =  fieldEntry.getValue();
                    String llaveValor = fieldName+"= '"+fieldValue.asText()+"'";
                    updateQuery.append(llaveValor);}
                }
            String updateQueString = updateQuery.toString();
            updateQueString += " "+queryWhere;
            jdbcTemplate.execute(updateQueString);                     
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @Override
        public Boolean actualizarAdicional(String request) {
        StringBuilder updateQuery = new StringBuilder("UPDATE BDD_");    
        try {
            JsonNode nodo = objectMapper.readTree(request);
            String queryWhere ="";
            String sql = "select centroCosto "+ 
                         "from CALIDAD_WEB.dbo.registrosCalidad "+
                         "where idRegistro =?";
            Iterator<Map.Entry<String, JsonNode>> fieldsIterator = nodo.fields();
            String centroCosto = jdbcTemplate.queryForObject(sql
                                                ,String.class
                                                ,nodo.get("idRegistro").asLong());
            updateQuery.append(centroCosto).append(".Gestion.Adicional SET ");
            boolean isFirstField = true;
            while(fieldsIterator.hasNext()){
                Map.Entry<String,JsonNode> fieldEntry = fieldsIterator.next();
                if(fieldEntry.getKey().equals("p_id")){
                    queryWhere = "WHERE p_id = "+fieldEntry.getValue();
                    continue;
                }
                if(!fieldEntry.getKey().equals("idRegistro")){
                    if(!isFirstField){
                        updateQuery.append(",");
                    }else{
                        isFirstField=false;
                    }
                    String fieldName =fieldEntry.getKey();
                    JsonNode fieldValue =  fieldEntry.getValue();
                    String llaveValor = fieldName+"= '"+fieldValue.asText()+"'";
                    updateQuery.append(llaveValor);}
                }
            String updateQueString = updateQuery.toString();
            updateQueString += " "+queryWhere;
            jdbcTemplate.execute(updateQueString);                     
            return true;
        } catch (Exception e) {
            return false;
        }
      
}}
