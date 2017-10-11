package com.sam.webappad.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "recursos")
public class RecursosEntity {
    
    @Id
    private Integer id;
    private String nombre;
    private int capacidad;
    private String comentarios;
    
    @OneToMany(mappedBy = "recursos_entity")
    private Set<ReservacionesEntity> reservaciones_entity;
    
    @OneToMany(mappedBy = "recursos_entity_inventario")
    private Set<InventarioEntity> inventario_entity;

    public RecursosEntity() {
    }

    public RecursosEntity(String nombre, int capacidad, String comentarios) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.comentarios = comentarios;
    }

    public int getId_recursos() {
        return id;
    }

    public void setId_recursos(int id_recursos) {
        this.id = id_recursos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public Set<ReservacionesEntity> getReservaciones_entity() {
        return reservaciones_entity;
    }

    public void setReservaciones_entity(Set<ReservacionesEntity> reservaciones_entity) {
        this.reservaciones_entity = reservaciones_entity;
    }

    public Set<InventarioEntity> getInventario_entity() {
        return inventario_entity;
    }

    public void setInventario_entity(Set<InventarioEntity> inventario_entity) {
        this.inventario_entity = inventario_entity;
    }
    
}
