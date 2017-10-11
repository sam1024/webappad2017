package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(InventarioEntity.class)
public abstract class InventarioEntity_ {

	public static volatile SingularAttribute<InventarioEntity, Integer> horas;
	public static volatile SingularAttribute<InventarioEntity, MarcasEntity> marcas_entity;
	public static volatile SingularAttribute<InventarioEntity, RecursosEntity> recursos_entity_inventario;
	public static volatile SingularAttribute<InventarioEntity, String> serie;
	public static volatile SingularAttribute<InventarioEntity, String> condiciones;
	public static volatile SetAttribute<InventarioEntity, PrestamoEntity> prestamo_entity;
	public static volatile SingularAttribute<InventarioEntity, Integer> id;
	public static volatile SingularAttribute<InventarioEntity, String> articulo;
	public static volatile SingularAttribute<InventarioEntity, String> comentarios;
	public static volatile SingularAttribute<InventarioEntity, Integer> status;
	public static volatile SingularAttribute<InventarioEntity, ModelosEntity> modelos_entity;

}

