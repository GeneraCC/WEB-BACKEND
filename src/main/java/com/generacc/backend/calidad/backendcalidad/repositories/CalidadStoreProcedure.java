package com.generacc.backend.calidad.backendcalidad.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlReturnResultSet;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import java.sql.Types;

public class CalidadStoreProcedure extends StoredProcedure {

    private final String nombreParametro;

    public CalidadStoreProcedure(JdbcTemplate jdbcTemplate, String calidadStoreProcedure,String nombreParametro) {
        super(jdbcTemplate, calidadStoreProcedure);
        this.nombreParametro=nombreParametro;

        declareParameter(new SqlParameter(nombreParametro, Types.INTEGER));
        declareParameter(new SqlReturnResultSet("resultado",(rs, rowNum) -> {
            Map<String, Object> mapaResultado = new HashMap<>();
            for (int i = 1; i<=rs.getMetaData().getColumnCount(); i++){
                mapaResultado.put(rs.getMetaData().getColumnName(i),rs.getObject(i));
            }
            return mapaResultado;
        }));
        compile();
    }

    public List<Map<String, Object>> ejecutar(Map<String, Object> parametros) { 
        return (List<Map<String, Object>>) super.execute(parametros).get("resultado"); 
    }
}
