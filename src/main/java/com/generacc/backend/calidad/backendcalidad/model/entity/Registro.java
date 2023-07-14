package com.generacc.backend.calidad.backendcalidad.model.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.generacc.backend.calidad.backendcalidad.model.calidad.ItemRegistroCalidad;

public class Registro {
    
    private List<ItemRegistroCalidad>  registros;
    private Long idUsuario;

    @Autowired
    public List<ItemRegistroCalidad> getRegistros() {
        return registros;
    }
    public void setRegistros(List<ItemRegistroCalidad>idCliente) {
        this.registros= idCliente;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
