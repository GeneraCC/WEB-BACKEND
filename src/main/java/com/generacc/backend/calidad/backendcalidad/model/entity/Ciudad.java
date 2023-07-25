package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Ciudad", schema = "Entidad", catalog = "Global")
public class Ciudad {
    @Id
    @Column(name = "idciudad")
    private Long idCiudad;
    @Column(name = "nombreciudad")
    private String nombreCiudad;    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idregion")
    private Region region;
    public Long getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }
    public String getNombreCiudad() {
        return nombreCiudad;
    }
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    public Region getRegion() {
        return region;
    }
    public void setRegion(Region region) {
        this.region = region;
    }
    
}
