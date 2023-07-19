package com.generacc.backend.calidad.backendcalidad.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "[Plan]", schema = "Entidad", catalog = "Global")
public class Plan {
    @Id
    @Column(name = "idplan")
    private Long idPlan;
    @Column(name = "descripcionplan")
    private String descripcionPlan;
    @Column(name = "primauf")
    private String primaUf;
    @Column(name = "primapesos")
    private String primaPesos;
    private Boolean activo;
    @Column(name = "centrocosto")
    private Long centroCosto;
    public Long getCentroCosto() {
        return centroCosto;
    }
    public void setCentroCosto(Long centroCosto) {
        this.centroCosto = centroCosto;
    }
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idtipocontrato")
    private TipoContrato tipoContrato; 

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }
    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public Long getIdPlan() {
        return idPlan;
    }
    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }
    public String getDescripcionPlan() {
        return descripcionPlan;
    }
    public void setDescripcionPlan(String descripcionPlan) {
        this.descripcionPlan = descripcionPlan;
    }
    public String getPrimaUf() {
        return primaUf;
    }
    public void setPrimaUf(String primaUf) {
        this.primaUf = primaUf;
    }
    public String getPrimaPesos() {
        return primaPesos;
    }
    public void setPrimaPesos(String primaPesos) {
        this.primaPesos = primaPesos;
    }
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
