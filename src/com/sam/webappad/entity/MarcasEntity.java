package com.sam.webappad.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "marcas")
public class MarcasEntity {
    
    @Id
    @Column(name = "id")
    private int id_marca;
    private String marca;
    
    @OneToMany(mappedBy = "marcas_entity")
    private Set<InventarioEntity> inventario_entity;
    
    @OneToMany(mappedBy = "marcas_entity_model")
    private Set<ModelosEntity> modelos_entity;

    public MarcasEntity() {
    }

    public MarcasEntity(String marca) {
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Set<InventarioEntity> getInventario_entity() {
        return inventario_entity;
    }

    public void setInventario_entity(Set<InventarioEntity> inventario_entity) {
        this.inventario_entity = inventario_entity;
    }
    
}
