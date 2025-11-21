package org.walkgo.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recorridos")
public class Recorrido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "distancia_km", nullable = false)
    private Double distanciaKm;

    @Column(name = "pasos", nullable = false)
    private Integer pasos;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    public Integer GetId() {
        return id;
    }

    public Integer GetIdUsuario() {
        return idUsuario;
    }

    public void SetIdUsuario(Integer _idUsuario) {
        idUsuario = _idUsuario;
    }

    public Double GetDistanciaKm() {
        return distanciaKm;
    }

    public void SetDistanciaKm(Double _distanciaKm) {
        distanciaKm = _distanciaKm;
    }

    public Integer GetPasos() {
        return pasos;
    }

    public void SetPasos(Integer _pasos) {
        pasos = _pasos;
    }

    public LocalDateTime GetFecha() {
        return fecha;
    }

    public void SetFecha(LocalDateTime _fecha) {
        fecha = _fecha;
    }
}