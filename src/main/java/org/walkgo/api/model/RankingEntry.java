package org.walkgo.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ranking")
public class RankingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ranking")
    private Integer idRanking;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "posicion", nullable = false)
    private Integer posicion;

    @Column(name = "estado", nullable = false, length = 10)
    private String estado;

    public Integer getIdRanking() {
        return idRanking;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getPosicion() {
        return posicion;
    }

    public void setPosicion(Integer posicion) {
        this.posicion = posicion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}