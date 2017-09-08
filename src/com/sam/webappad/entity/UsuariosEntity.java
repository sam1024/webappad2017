package com.sam.webappad.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "USUARIOS")
public class UsuariosEntity {
    
    @Id
    @SequenceGenerator(name = "seq", sequenceName = "SEQUENCE_USUARIOS")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")    
    @Column(name = "id")
    private int id;
    private String usuario;
    private String pass;
    @Column(name = "id_roles")
    private int id_rol;
    private String nombre_completo;    
    
    @OneToMany(mappedBy = "usuarios_entity")
    private Set<ReservacionesEntity> reservaciones_entity;
    
    @ManyToOne
    @JoinColumn(name = "id_roles", insertable = false, updatable = false)
    private RolesEntity roles_entity;

    public UsuariosEntity() {
    }

    public UsuariosEntity(String usuario, String pass, String nombre_completo) {
        this.usuario = usuario;
        this.pass = pass;
        this.nombre_completo = nombre_completo;
    }

    public int getId_usuario() {
        return id;
    }

    public void setId_usuario(int id_usuario) {
        this.id = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre_completo() {
        return nombre_completo;
    }

    public void setNombre_completo(String nombre_completo) {
        this.nombre_completo = nombre_completo;
    }

    public RolesEntity getRoles_entity() {
        return roles_entity;
    }

    public void setRoles_entity(RolesEntity roles_entity) {
        this.roles_entity = roles_entity;
    }

    public Set<ReservacionesEntity> getReservaciones_entity() {
        return reservaciones_entity;
    }

    public void setReservaciones_entity(Set<ReservacionesEntity> reservaciones_entity) {
        this.reservaciones_entity = reservaciones_entity;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    @Override
    public String toString() {
        return "UsuariosEntity{" + "id_usuario=" + id + ", usuario=" + usuario + ", pass=" + pass + ", nombre_completo=" + nombre_completo + '}';
    }
}