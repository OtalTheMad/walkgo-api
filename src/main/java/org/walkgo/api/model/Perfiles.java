package org.walkgo.api.model;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "perfiles")
public class Perfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Integer id_perfil;

    @Column(name = "id_usuario", nullable = false)
    private Integer id_usuario;

    @Lob
    @Column(name = "foto")
    private byte[] foto;

    @Column(name = "pais")
    private String pais;

    @Column(name = "biografia")
    private String biografia;

    @Column(name = "fecha_nac")
    private Date fecha_nac;

    @Column(name = "estado")
    private String estado;

    public int getId_perfil() { return id_perfil; }
    public void setId_perfil(int id_perfil) { this.id_perfil = id_perfil; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }

    public byte[] getFoto() { return foto; }
    public void setFoto(byte[] foto) { this.foto = foto; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getBiografia() { return biografia; }
    public void setBiografia(String biografia) { this.biografia = biografia; }

    public Date getFecha_nac() { return fecha_nac; }
    public void setFecha_nac(Date fecha_nac) { this.fecha_nac = fecha_nac; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}