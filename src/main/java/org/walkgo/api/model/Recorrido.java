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

    public Integer getId() {
        return id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(Double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public Integer getPasos() {
        return pasos;
    }

    public void setPasos(Integer pasos) {
        this.pasos = pasos;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}