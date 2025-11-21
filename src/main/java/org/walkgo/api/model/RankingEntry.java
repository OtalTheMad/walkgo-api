package org.walkgo.api.model;

public class RankingEntry {

    private Integer userId;
    private String usuario;
    private Integer posicion;
    private Integer rangoSemanal;
    private Double totalDistanciaKm;
    private String avatar;

    public RankingEntry() {
    }

    public RankingEntry(Integer userId, String usuario, Integer posicion, Integer rangoSemanal, Double totalDistanciaKm, String avatar) {
        this.userId = userId;
        this.usuario = usuario;
        this.posicion = posicion;
        this.rangoSemanal = rangoSemanal;
        this.totalDistanciaKm = totalDistanciaKm;
        this.avatar = avatar;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public Integer getRangoSemanal() {
        return rangoSemanal;
    }

    public void setRangoSemanal(Integer rangoSemanal) {
        this.rangoSemanal = rangoSemanal;
    }

    public Double getTotalDistanciaKm() {
        return totalDistanciaKm;
    }

    public void setTotalDistanciaKm(Double totalDistanciaKm) {
        this.totalDistanciaKm = totalDistanciaKm;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}