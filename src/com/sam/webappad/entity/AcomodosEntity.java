package com.sam.webappad.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "acomodos")
public class AcomodosEntity {
	
	@Id
	private int id;
	private String acomodos;
	
	@OneToMany(mappedBy = "acomodos_entity")
	private Set<ReservacionesEntity> reservaciones_entity_id_acomodo;

	public AcomodosEntity() { }

	public AcomodosEntity(String acomodos) {
		this.acomodos = acomodos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAcomodos() {
		return acomodos;
	}

	public void setAcomodos(String acomodos) {
		this.acomodos = acomodos;
	}

	public Set<ReservacionesEntity> getReservaciones_entity_id_acomodo() {
		return reservaciones_entity_id_acomodo;
	}

	public void setReservaciones_entity_id_acomodo(Set<ReservacionesEntity> reservaciones_entity_id_acomodo) {
		this.reservaciones_entity_id_acomodo = reservaciones_entity_id_acomodo;
	}
	
	/***** SI SE SOBREESCRIBE EL METODO TOSTRING Y SE MANDA A LLAMAR UNA FUNCION DONDE SE REGRESE UN OBJETO
	       DEL MISMO TIPO DE LA CLASE MARCA EL ERROR DE QUE NO SE PUEDE INICIALIZAR EL PROXY *****/

//	@Override
//	public String toString() {
//		return "AcomodosEntity [id=" + id + ", acomodos=" + acomodos + ", reservaciones_entity_id_acomodo="
//				+ reservaciones_entity_id_acomodo + "]";
//	}
	

}

