package org.walkgo.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "amigos",
       uniqueConstraints = @UniqueConstraint(columnNames = {"id_usuario", "id_usuario_amigo"}))
public class Amigo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_amigo")
    private Integer id_amigo;

    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;

    @Column(name = "id_usuario_amigo", nullable = false)
    private Integer id_usuario_amigo;

    @Column(name = "estado", nullable = false)
    private String estado = "activo";

    public Integer GetId_Amigo() {
        return id_amigo;
    }

    public void SetId_Amigo(Integer id_amigo) {
        this.id_amigo = id_amigo;
    }

    public Integer GetId_Usuario() {
        return id_usuario;
    }

    public void SetId_Usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer GetId_Usuario_Amigo() {
        return id_usuario_amigo;
    }

    public void SetId_Usuario_Amigo(Integer id_usuario_amigo) {
        this.id_usuario_amigo = id_usuario_amigo;
    }

    public String GetEstado() {
        return estado;
    }

    public void SetEstado(String estado) {
        this.estado = estado;
    }
}