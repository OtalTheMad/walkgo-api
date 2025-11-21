package org.walkgo.api.model;

public class FinalizarRecorridoRequest {

    private Double _distanciaSesionKm;
    private Integer _pasosSesion;

    public Double GetDistanciaSesionKm() {
        return _distanciaSesionKm;
    }

    public void SetDistanciaSesionKm(Double _distanciaSesionKm) {
        this._distanciaSesionKm = _distanciaSesionKm;
    }

    public Integer GetPasosSesion() {
        return _pasosSesion;
    }

    public void SetPasosSesion(Integer _pasosSesion) {
        this._pasosSesion = _pasosSesion;
    }
}