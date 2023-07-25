package com.generacc.backend.calidad.backendcalidad.model.dto;

import java.util.List;

import com.generacc.backend.calidad.backendcalidad.model.entity.ParentescoCampana;

public class TipoPersonaDto {
    private Long idTipoPersona;
    public Long getIdTipoPersona() {
        return idTipoPersona;
    }
    public void setIdTipoPersona(Long idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }
    public String getTipoPersona() {
        return tipoPersona;
    }
    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
    public List<ParentescoCampana> getParentescoCampanas() {
        return parentescoCampanas;
    }
    public void setParentescoCampanas(List<ParentescoCampana> parentescoCampanas) {
        this.parentescoCampanas = parentescoCampanas;
    }
    private String tipoPersona;
    private List<ParentescoCampana> parentescoCampanas;
}
