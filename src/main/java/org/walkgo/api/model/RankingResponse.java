package org.walkgo.api.model;

public class RankingResponse {

    private Integer posicion;
    private Integer idUsuario;
    private String usuario;
    private Double totalDistanciaKm;

    public RankingResponse() {
    }

    public RankingResponse(Integer posicion, Integer idUsuario, String usuario, Double totalDistanciaKm) {
        this.posicion = posicion;
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.totalDistanciaKm = totalDistanciaKm;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Double getTotalDistanciaKm() {
        return totalDistanciaKm;
    }

    public void setTotalDistanciaKm(Double totalDistanciaKm) {
        this.totalDistanciaKm = totalDistanciaKm;
    }
}