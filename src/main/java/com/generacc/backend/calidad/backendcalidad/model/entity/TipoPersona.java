package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipopersona", schema = "Entidad", catalog = "Global")
public class TipoPersona {
    @Id
    @Column(name = "idtipopersona")
    private Long idTipoPersona;
    @Column(name = "tipopersona")
    private String tipoPersona;
    public Long getIdTipoPersona() {
        return idTipoPersona;
    }
    public void setIdTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }
    public String getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
}
