package org.walkgo.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer _id;

    @Column(name = "usuario", nullable = false, length = 30)
    private String _usuario;

    @Column(name = "clave", nullable = false, length = 30)
    private String _clave;

    @Column(name = "total_pasos", nullable = false)
    private Integer _total_pasos = 0;

    @Column(name = "total_distancia_km", nullable = false)
    private Double _total_distancia_km = 0.0;

    @Column(name = "total_pasos_semanales", nullable = false)
    private Integer _total_pasos_semanales = 0;

    @Column(name = "rango_semanal", nullable = false)
    private Integer _rango_semanal = 0;

    @Column(name = "creado_en", nullable = false)
    private LocalDateTime _creado_en = LocalDateTime.now();

    @Column(name = "actualizado_en", nullable = false)
    private LocalDateTime _actualizado_en = LocalDateTime.now();

    @Column(name = "estado", nullable = false)
    private Boolean _estado = true;


    public Integer getId() {
        return _id;
    }
    public void setId(Integer _id) {
        this._id = _id;
    }

    public String getUsuario() {
        return _usuario;
    }
    public void setUsuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String getClave() {
        return _clave;
    }
    public void setClave(String _clave) {
        this._clave = _clave;
    }

    public Integer getTotalPasos() {
        return _total_pasos;
    }
    public void setTotalPasos(Integer _total_pasos) {
        this._total_pasos = _total_pasos;
    }

    public Double getTotalDistanciaKm() {
        return _total_distancia_km;
    }
    public void setTotalDistanciaKm(Double _total_distancia_km) {
        this._total_distancia_km = _total_distancia_km;
    }

    public Integer getTotalPasosSemanales() {
        return _total_pasos_semanales;
    }
    public void setTotalPasosSemanales(Integer _total_pasos_semanales) {
        this._total_pasos_semanales = _total_pasos_semanales;
    }

    public Integer getRangoSemanal() {
        return _rango_semanal;
    }
    public void setRangoSemanal(Integer _rango_semanal) {
        this._rango_semanal = _rango_semanal;
    }

    public LocalDateTime getCreadoEn() {
        return _creado_en;
    }
    public void setCreadoEn(LocalDateTime _creado_en) {
        this._creado_en = _creado_en;
    }

    public LocalDateTime getActualizadoEn() {
        return _actualizado_en;
    }
    public void setActualizadoEn(LocalDateTime _actualizado_en) {
        this._actualizado_en = _actualizado_en;
    }

    public Boolean getEstado() {
        return _estado;
    }
    public void setEstado(Boolean _estado) {
        this._estado = _estado;
    }
}