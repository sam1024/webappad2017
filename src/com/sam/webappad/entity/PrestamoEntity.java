package com.sam.webappad.entity;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "prestamo")
public class PrestamoEntity {
    
    @Id
    private int id;
    private String nombre;
    private Date fecha;
    private int status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_inventario")
    private InventarioEntity inventario_entity;

    public PrestamoEntity() {
    }

    public PrestamoEntity(String nombre, Date fecha, int status) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public InventarioEntity getInventario_entity() {
        return inventario_entity;
    }

    public void setInventario_entity(InventarioEntity inventario_entity) {
        this.inventario_entity = inventario_entity;
    }
    
}
