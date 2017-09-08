package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RecursosEntity.class)
public abstract class RecursosEntity_ {

	public static volatile SetAttribute<RecursosEntity, InventarioEntity> inventario_entity;
	public static volatile SetAttribute<RecursosEntity, ReservacionesEntity> reservaciones_entity;
	public static volatile SingularAttribute<RecursosEntity, Integer> id;
	public static volatile SingularAttribute<RecursosEntity, String> nombre;
	public static volatile SingularAttribute<RecursosEntity, String> comentarios;
	public static volatile SingularAttribute<RecursosEntity, Integer> capacidad;

}

