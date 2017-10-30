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
@Table(name = "inventarios")
public class InventarioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "new_register", allocationSize = 1)
    private int id;
    private String serie;
    private int horas;
    private String condiciones;
    private String comentarios;
    private int status;
    
    @ManyToOne
    @JoinColumn(name = "id_recurso", insertable = true, updatable = true)
    private RecursosEntity recursos_entity_inventario;
    
    @ManyToOne
    @JoinColumn(name = "id_marca", insertable = true, updatable = true)
    private MarcasEntity marcas_entity;
    
    @ManyToOne
    @JoinColumn(name = "id_modelo", insertable = true, updatable = true)
    private ModelosEntity modelos_entity;
    
    @OneToMany(mappedBy = "inventario_entity")
    private Set<PrestamoEntity> prestamo_entity;
    
    @ManyToOne
    @JoinColumn(name = "id_articulo", insertable = true, updatable = true)
    private ArticulosEntity articulos_entity;

    public InventarioEntity() {
    }

    public InventarioEntity(String serie, int horas, String condiciones, String comentarios, int status) {
        this.serie = serie;
    	this.horas = horas;
        this.condiciones = condiciones;
        this.comentarios = comentarios;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

	public ModelosEntity getModelos_entity() {
		return modelos_entity;
	}

	public void setModelos_entity(ModelosEntity modelos_entity) {
		this.modelos_entity = modelos_entity;
	}

	public Set<PrestamoEntity> getPrestamo_entity() {
		return prestamo_entity;
	}

	public void setPrestamo_entity(Set<PrestamoEntity> prestamo_entity) {
		this.prestamo_entity = prestamo_entity;
	}

	public ArticulosEntity getArticulos_entity() {
		return articulos_entity;
	}

	public void setArticulos_entity(ArticulosEntity articulos_entity) {
		this.articulos_entity = articulos_entity;
	}

	@Override
	public String toString() {
		return "InventarioEntity [id=" + id + ", horas=" + horas + ", condiciones=" + condiciones
				+ ", comentarios=" + comentarios + ", status=" + status + ", recursos_entity_inventario="
				+ recursos_entity_inventario + ", marcas_entity=" + marcas_entity + ", modelos_entity=" + modelos_entity
				+ ", prestamo_entity=" + prestamo_entity + ", articulos_entity=" + articulos_entity + "]";
	}
	 
    
}
