package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "Perfil", schema = "Entidad")
public class Perfil {

    
    @Id
    @Column(name = "idperfil")
    private Long idPerfil;

    @Column(name = "nombreperfil")
    private String nombrePerfil;

    private Boolean activo;

    public Long getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Perfil() {
    }

    public Perfil(Long idPerfil) {
        this.idPerfil = idPerfil;
    }
    
}
