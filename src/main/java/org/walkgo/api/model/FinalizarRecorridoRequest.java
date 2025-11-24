package org.walkgo.api.model;

public class FinalizarRecorridoRequest {

    private Double distanciaSesionKm;
    private Integer pasosSesion;

    public Double getDistanciaSesionKm() {
        return distanciaSesionKm;
    }

    public void setDistanciaSesionKm(Double distanciaSesionKm) {
        this.distanciaSesionKm = distanciaSesionKm;
    }

    public Integer getPasosSesion() {
        return pasosSesion;
    }

    public void setPasosSesion(Integer pasosSesion) {
        this.pasosSesion = pasosSesion;
    }
}