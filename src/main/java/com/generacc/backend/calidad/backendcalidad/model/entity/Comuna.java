package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Comuna", schema = "Entidad", catalog = "Global")
public class Comuna {
    @Id
    @Column(name = "idcomuna")
    private Long idComuna;

    @Column(name = "nombrecomuna")
    private String nombreComuna;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idciudad")
    private Ciudad ciudad;

    public Long getIdComuna() {
        return idComuna;
    }

    public void setIdComuna(Long idComuna) {
        this.idComuna = idComuna;
    }

    public String getNombreComuna() {
        return nombreComuna;
    }

    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    private Boolean activo;

}
