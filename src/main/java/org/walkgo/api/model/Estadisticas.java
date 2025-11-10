package org.walkgo.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estadisticas")
public class Estadisticas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica")
    private Integer id_estadistica;

    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;

    @Column(name = "km_recorrido", nullable = false)
    private Integer km_recorrido;

    @Column(name = "calorias_quemadas")
    private String calorias_quemadas;

    @Column(name = "clasificacion")
    private String clasificacion;

    @Column(name = "estado")
    private String estado;

    public int getId_estadistica() { return id_estadistica; }
    public void setId_estadistica(int id_estadistica) { this.id_estadistica = id_estadistica; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public int getKm_recorrido() { return km_recorrido; }
    public void setKm_recorrido(int km_recorrido) { this.km_recorrido = km_recorrido; }

    public String getCalorias_quemadas() { return calorias_quemadas; }
    public void setCalorias_quemadas(String calorias_quemadas) { this.calorias_quemadas = calorias_quemadas; }

    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}