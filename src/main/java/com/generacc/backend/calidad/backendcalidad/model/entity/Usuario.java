package com.generacc.backend.calidad.backendcalidad.model.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "Usuario", schema = "Sesion")
public class Usuario {

    @Id
    @Column(name = "idusuario")
    private Long idusuario;
    
    @Column(name = "nombreusuario")
    private String nombreusuario;

    private String password;

    @ManyToMany
    @JoinTable(name = "Perfilusuario",schema = "Config"
    ,joinColumns = @JoinColumn(name="idusuario")
    ,inverseJoinColumns = @JoinColumn(name="idperfil")
    ,uniqueConstraints = {@UniqueConstraint(columnNames = {"idusuario","idperfil"})})
    private List<Perfil> perfil;

    public Long getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Long idusuario) {
        this.idusuario = idusuario;
    }

    public String getNombreUsuario() {
        return nombreusuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreusuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Perfil> getPerfil() {
        return perfil;
    }

    public void setPerfil(List<Perfil> perfil) {
        this.perfil = perfil;
    }

    
}
