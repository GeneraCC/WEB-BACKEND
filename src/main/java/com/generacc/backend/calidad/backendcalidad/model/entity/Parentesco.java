package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Parentesco", schema = "Entidad", catalog = "Global")
public class Parentesco {
    @Id
    @Column(name = "idparentesco")
    private Long idParentesco;
    @Column(name = "nombreparentesco")
    private String nombreParentesco;
    public Long getIdParentesco() {
        return idParentesco;
    }
    public void setIdParentesco(Long idParentesco) {
        this.idParentesco = idParentesco;
    }
    public String getNombreParentesco() {
        return nombreParentesco;
    }
    public void setNombreParentesco(String nombreParentesco) {
        this.nombreParentesco = nombreParentesco;
    };
}
