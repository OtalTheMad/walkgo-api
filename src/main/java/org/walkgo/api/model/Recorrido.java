package org.walkgo.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recorridos")
public class Recorrido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer _id;

    @Column(name = "id_usuario", nullable = false)
    private Integer _idUsuario;

    @Column(name = "distancia_km", nullable = false)
    private Double _distanciaKm;

    @Column(name = "pasos", nullable = false)
    private Integer _pasos;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime _fecha;

    public Integer getId() {
        return _id;
    }

    public Integer getIdUsuario() {
        return _idUsuario;
    }

    public void setIdUsuario(Integer _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public Double getDistanciaKm() {
        return _distanciaKm;
    }

    public void setDistanciaKm(Double _distanciaKm) {
        this._distanciaKm = _distanciaKm;
    }

    public Integer getPasos() {
        return _pasos;
    }

    public void setPasos(Integer _pasos) {
        this._pasos = _pasos;
    }

    public LocalDateTime getFecha() {
        return _fecha;
    }

    public void setFecha(LocalDateTime _fecha) {
        this._fecha = _fecha;
    }
}