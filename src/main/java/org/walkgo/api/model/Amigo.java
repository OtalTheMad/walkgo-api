package org.walkgo.api.model;

import jakarta.persistence.*;

@Entity

public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amigo")
    private Integer idAmigo;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "id_usuario_amigo", nullable = false)
    private Integer idUsuarioAmigo;

    @Column(name = "estado", nullable = false)
    private String estado = "activo";

    public Integer getIdAmigo() {
        return idAmigo;
    }

    public void setIdAmigo(Integer idAmigo) {
        this.idAmigo = idAmigo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuarioAmigo() {
        return idUsuarioAmigo;
    }

    public void setIdUsuarioAmigo(Integer idUsuarioAmigo) {
        this.idUsuarioAmigo = idUsuarioAmigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}