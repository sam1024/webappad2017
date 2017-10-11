package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MarcasEntity.class)
public abstract class MarcasEntity_ {

	public static volatile SingularAttribute<MarcasEntity, String> marca;
	public static volatile SetAttribute<MarcasEntity, InventarioEntity> inventario_entity;
	public static volatile SingularAttribute<MarcasEntity, Integer> id;
	public static volatile SetAttribute<MarcasEntity, ModelosEntity> modelos_entity;

}

