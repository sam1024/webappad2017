package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(AcomodosEntity.class)
public abstract class AcomodosEntity_ {

	public static volatile SingularAttribute<AcomodosEntity, String> acomodos;
	public static volatile SingularAttribute<AcomodosEntity, Integer> id;
	public static volatile SetAttribute<AcomodosEntity, ReservacionesEntity> reservaciones_entity_id_acomodo;

}

