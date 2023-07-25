package com.generacc.backend.calidad.backendcalidad.model.calidad;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Poliza {

    private Long idPoliza;
    private Long rut;
    private String nombre;
    private String paterno;
    private String materno;
    private String fechaNacimiento;
    private String sexo;
    private String direccion;
    private String comuna;
    private String ciudad;
    private String peso;
    private String estatura;

    
    public Long getIdPoliza() {
        return idPoliza;
    }
    public void setIdPoliza(Long idPoliza) {
        this.idPoliza = idPoliza;
    }
    public Long getRut() {
        return rut;
    }
    public void setRut(Long rut) {
        this.rut = rut;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPaterno() {
        return paterno;
    }
    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }
    public String getMaterno() {
        return materno;
    }
    public void setMaterno(String materno) {
        this.materno = materno;
    }
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getComuna() {
        return comuna;
    }
    public void setComuna(String comuna) {
        this.comuna = comuna;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getPeso() {
        return peso;
    }
    public void setPeso(String peso) {
        this.peso = peso;
    }
    public String getEstatura() {
        return estatura;
    }
    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    
}
