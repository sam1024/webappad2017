package com.sam.webappad.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "inventario")
public class InventarioEntity {
    
    @Id
    @Column(name = "id")
    private int id_inventario;
    private String articulo;
    private String serie;
    private int horas;
    private String condiciones;
    private String comentarios;
    private int status;
    
    @ManyToOne
    @JoinColumn(name = "id_recursos")
    private RecursosEntity recursos_entity_inventario;
    
    @ManyToOne
    @JoinColumn(name = "id_marca")
    private MarcasEntity marcas_entity;
    
    @OneToMany(mappedBy = "inventario_entity")
    private Set<PrestamoEntity> prestamo_entity;

    public InventarioEntity() {
    }

    public InventarioEntity(String articulo, String serie, int horas, String condiciones, String comentarios, int status) {
        this.articulo = articulo;
        this.serie = serie;
        this.horas = horas;
        this.condiciones = condiciones;
        this.comentarios = comentarios;
        this.status = status;
    }

    public int getId() {
        return id_inventario;
    }

    public void setId(int id) {
        this.id_inventario = id;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getCondiciones() {
        return condiciones;
    }

    public void setCondiciones(String condiciones) {
        this.condiciones = condiciones;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RecursosEntity getRecursos_entity_inventario() {
        return recursos_entity_inventario;
    }

    public void setRecursos_entity_inventario(RecursosEntity recursos_entity_inventario) {
        this.recursos_entity_inventario = recursos_entity_inventario;
    }

    public MarcasEntity getMarcas_entity() {
        return marcas_entity;
    }

    public void setMarcas_entity(MarcasEntity marcas_entity) {
        this.marcas_entity = marcas_entity;
    }
    
}
