package com.sam.webappad.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "modelos")
public class ModelosEntity {
    
    @Id
    private int id;
    private String modelo;
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcasEntity marcas_entity_model;
    
    @OneToMany(mappedBy = "modelos_entity")
    private Set<InventarioEntity> inventario_entity;

    public ModelosEntity() {
    }

    public ModelosEntity(String modelo) {
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public MarcasEntity getMarcas_entity_model() {
        return marcas_entity_model;
    }

    public void setMarcas_entity_model(MarcasEntity marcas_entity_model) {
        this.marcas_entity_model = marcas_entity_model;
    }
    
}
