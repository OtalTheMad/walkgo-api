package org.walkgo.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long _id;

    @Column(name = "usuario", nullable = false, length = 30)
    private String _usuario;

    @Column(name = "clave", nullable = false, length = 30)
    private String _clave;

    @Column(name = "total_pasos", nullable = false)
    private Integer _total_pasos = 0;

    @Column(name = "total_distancia_km", nullable = false, columnDefinition = "DECIMAL(65,0) DEFAULT 0")
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

    // -------- Getters and Setters --------

    public Long GetId() {
        return _id;
    }

    public void SetId(Long _id) {
        this._id = _id;
    }

    public String GetUsuario() {
        return _usuario;
    }

    public void SetUsuario(String _usuario) {
        this._usuario = _usuario;
    }

    public String GetClave() {
        return _clave;
    }

    public void SetClave(String _clave) {
        this._clave = _clave;
    }

    public Integer GetTotal_Pasos() {
        return _total_pasos;
    }

    public void SetTotal_Pasos(Integer _total_pasos) {
        this._total_pasos = _total_pasos;
    }

    public Double GetTotal_Distancia_Km() {
        return _total_distancia_km;
    }

    public void SetTotal_Distancia_Km(Double _total_distancia_km) {
        this._total_distancia_km = _total_distancia_km;
    }

    public Integer GetTotal_Pasos_Semanales() {
        return _total_pasos_semanales;
    }

    public void SetTotal_Pasos_Semanales(Integer _total_pasos_semanales) {
        this._total_pasos_semanales = _total_pasos_semanales;
    }

    public Integer GetRango_Semanal() {
        return _rango_semanal;
    }

    public void SetRango_Semanal(Integer _rango_semanal) {
        this._rango_semanal = _rango_semanal;
    }

    public LocalDateTime GetCreado_En() {
        return _creado_en;
    }

    public void SetCreado_En(LocalDateTime _creado_en) {
        this._creado_en = _creado_en;
    }

    public LocalDateTime GetActualizado_En() {
        return _actualizado_en;
    }

    public void SetActualizado_En(LocalDateTime _actualizado_en) {
        this._actualizado_en = _actualizado_en;
    }

    public Boolean GetEstado() {
        return _estado;
    }

    public void SetEstado(Boolean _estado) {
        this._estado = _estado;
    }
}
