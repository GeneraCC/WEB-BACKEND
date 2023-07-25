package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "parentescocampaña",schema = "Config", catalog = "Global")
public class ParentescoCampana {
    @Id
    @Column(name = "idparentescocampaña")
    private Long idParentescoCampana;
    @Column(name = "centrocosto")
    private Long centroCosto;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipopersona")
    private TipoPersona tipoPersona;

    public ParentescoCampana(){}

    public ParentescoCampana(Long idParentescoCampana, Long centroCosto, Long edadMin, Long edadMax,Parentesco parentesco, Boolean activo) {
        this.idParentescoCampana = idParentescoCampana;
        this.centroCosto = centroCosto;
        this.edadMin = edadMin;
        this.edadMax = edadMax;
        this.parentesco = parentesco;
        this.activo = activo;
    }
    @Column(name = "edadmin")
    private Long edadMin;
    @Column(name = "edadmax")
    private Long edadMax;
    private Boolean activo;
    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idparentesco")
    private Parentesco parentesco;
    public Long getIdParentescoCampana() {
        return idParentescoCampana;
    }
    public void setIdParentescoCampana(Long idParentescoCampana) {
        this.idParentescoCampana = idParentescoCampana;
    }
    public Long getCentroCosto() {
        return centroCosto;
    }
    public void setCentroCosto(Long centroCosto) {
        this.centroCosto = centroCosto;
    }
    public Long getEdadMin() {
        return edadMin;
    }
    public void setEdadMin(Long edadMin) {
        this.edadMin = edadMin;
    }
    public Long getEdadMax() {
        return edadMax;
    }
    public void setEdadMax(Long edadMax) {
        this.edadMax = edadMax;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    public Parentesco getParentesco() {
        return parentesco;
    }
    public void setParentesco(Parentesco parentesco) {
        this.parentesco = parentesco;
    }
    
}
