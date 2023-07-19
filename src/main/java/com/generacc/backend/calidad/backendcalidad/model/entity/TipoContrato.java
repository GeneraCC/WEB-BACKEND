package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Tipocontrato", schema = "Entidad", catalog = "Global")
public class TipoContrato {
    @Id
    @Column(name = "idtipocontrato")
    private Long idTipoContrato;
    @Column(name = "nombretipocontrato")
    private String nombreTipoContrato;
    private Long orden;
    public Long getIdTipoContrato() {
        return idTipoContrato;
    }
    public void setIdTipoContrato(Long idTipoContrato) {
        this.idTipoContrato = idTipoContrato;
    }
    public String getNombreTipoContrato() {
        return nombreTipoContrato;
    }
    public void setNombreTipoContrato(String nombreTipoContrato) {
        this.nombreTipoContrato = nombreTipoContrato;
    }
    public Long getOrden() {
        return orden;
    }
    public void setOrden(Long orden) {
        this.orden = orden;
    } 
}
