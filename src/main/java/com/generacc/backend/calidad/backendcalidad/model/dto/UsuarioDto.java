package com.generacc.backend.calidad.backendcalidad.model.dto;

public class UsuarioDto {
    private Long idusuario;
    private String nombreusuario;

    public UsuarioDto() {
    }
    public UsuarioDto(Long idusuario, String nombreusuario) {
        this.idusuario = idusuario;
        this.nombreusuario = nombreusuario;
    }
    public Long getIdusuario() {
        return idusuario;
    }
    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }
    public String getNombreusuario() {
        return nombreusuario;
    }
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    

}
