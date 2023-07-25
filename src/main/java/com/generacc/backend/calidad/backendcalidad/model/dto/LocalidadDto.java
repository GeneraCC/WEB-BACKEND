package com.generacc.backend.calidad.backendcalidad.model.dto;

public class LocalidadDto {
    private Long idComuna;
    private Long idCiudad;
    private Long idRegion;
    private String nombreComuna;
    private String nombreCiudad;
    private String nombreRegion;

    public LocalidadDto(Long idComuna, String nombreComuna, Long idCiudad, String nombreCiudad, Long idRegion, String nombreRegion) {
        this.idComuna = idComuna;
        this.nombreComuna = nombreComuna;
        this.idCiudad = idCiudad;
        this.nombreCiudad = nombreCiudad;
        this.idRegion = idRegion;
        this.nombreRegion = nombreRegion;
    }

    public Long getIdComuna() {
        return idComuna;
    }
    public void setIdComuna(Long idComuna) {
        this.idComuna = idComuna;
    }
    public Long getIdCiudad() {
        return idCiudad;
    }
    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }
    public Long getIdRegion() {
        return idRegion;
    }
    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }
    public String getNombreComuna() {
        return nombreComuna;
    }
    public void setNombreComuna(String nombreComuna) {
        this.nombreComuna = nombreComuna;
    }
    public String getNombreCiudad() {
        return nombreCiudad;
    }
    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }
    public String getNombreRegion() {
        return nombreRegion;
    }
    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }
}
