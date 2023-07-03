package com.generacc.backend.calidad.backendcalidad.model.calidad;

public class ItemRegistroCalidad {

    private long idRegistro;
    private int idCampaña;
    public long getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(long idRegistro) {
        this.idRegistro = idRegistro;
    }
    public int getIdCampaña() {
        return idCampaña;
    }
    public void setIdCampaña(int idCampaña) {
        this.idCampaña = idCampaña;
    }
    @Override
    public String toString() {
        return "ItemRegistroCalidad [idRegistro=" + idRegistro + ", idCampaña=" + idCampaña + "]";
    }
    
}
