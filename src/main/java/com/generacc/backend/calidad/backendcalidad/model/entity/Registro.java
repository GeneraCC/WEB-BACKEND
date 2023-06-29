package com.generacc.backend.calidad.backendcalidad.model.entity;

import java.util.List;
import java.util.Map;

public class Registro {
    
    private List<Map<Long, Integer>> registros;
    private Long idUsuario;

    public List<Map<Long,Integer>> getRegistros() {
        return registros;
    }
    public void setRegistros(List<Map<Long,Integer>> idCliente) {
        this.registros= idCliente;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
