package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Region", schema = "Entidad", catalog = "Global")
public class Region {
    @Id
    @Column(name = "idregion")
    private Long idRegion;
    @Column(name = "nombreregion")
    private String nombreRegion;
    public Long getIdRegion() {
        return idRegion;
    }
    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }
    public String getNombreRegion() {
        return nombreRegion;
    }
    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }
}
