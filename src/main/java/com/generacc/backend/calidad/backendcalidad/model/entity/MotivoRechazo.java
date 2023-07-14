package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Motivo", catalog = "CALIDAD_WEB", schema = "dbo")
public class MotivoRechazo {
    @Id
    @Column(name = "idmotivo")
    private Long idMotivo;

    private String descripcion;

    @Column(name = "idinstancia")
    private long idInstancia;

    @Column(name = "idpadre")
    private Long idPadre;
    private Boolean activo;
    
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Long getIdMotivo() {
        return idMotivo;
    }
    public void setIdMotivo(Long idMotivo) {
        this.idMotivo = idMotivo;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public long getIdInstancia() {
        return idInstancia;
    }
    public void setIdInstancia(long idInstancia) {
        this.idInstancia = idInstancia;
    }
    public Long getIdPadre() {
        return idPadre;
    }
    public void setIdPadre(Long idPadre) {
        this.idPadre = idPadre;
    }
}
