package com.generacc.backend.calidad.backendcalidad.model.calidad;

public class ItemRegistroCalidad {

    private long idRegistro;
    private int idCampana;
    public long getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(long idRegistro) {
        this.idRegistro = idRegistro;
    }
    public int getIdCampana() {
        return idCampana;
    }
    public void setIdCampaña(int idCampana) {
        this.idCampana = idCampana;
    }
    @Override
    public String toString() {
        return "ItemRegistroCalidad [idRegistro=" + idRegistro + ", idCampaña=" + idCampana + "]";
    }
    
}
