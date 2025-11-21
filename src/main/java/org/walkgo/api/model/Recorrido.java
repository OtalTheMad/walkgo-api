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

    public Integer GetId() {
        return _id;
    }

    public Integer GetIdUsuario() {
        return _idUsuario;
    }

    public void SetIdUsuario(Integer _idUsuario) {
        this._idUsuario = _idUsuario;
    }

    public Double GetDistanciaKm() {
        return _distanciaKm;
    }

    public void SetDistanciaKm(Double _distanciaKm) {
        this._distanciaKm = _distanciaKm;
    }

    public Integer GetPasos() {
        return _pasos;
    }

    public void SetPasos(Integer _pasos) {
        this._pasos = _pasos;
    }

    public LocalDateTime GetFecha() {
        return _fecha;
    }

    public void SetFecha(LocalDateTime _fecha) {
        this._fecha = _fecha;
    }
}