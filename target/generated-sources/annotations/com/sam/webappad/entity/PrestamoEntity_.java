package com.sam.webappad.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(PrestamoEntity.class)
public abstract class PrestamoEntity_ {

	public static volatile SingularAttribute<PrestamoEntity, Date> fecha;
	public static volatile SingularAttribute<PrestamoEntity, InventarioEntity> inventario_entity;
	public static volatile SingularAttribute<PrestamoEntity, Integer> id;
	public static volatile SingularAttribute<PrestamoEntity, String> nombre;
	public static volatile SingularAttribute<PrestamoEntity, Integer> status;

}

