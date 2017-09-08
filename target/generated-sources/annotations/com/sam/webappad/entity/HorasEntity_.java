package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(HorasEntity.class)
public abstract class HorasEntity_ {

	public static volatile SetAttribute<HorasEntity, ReservacionesEntity> reservaciones_entity_id_horaini;
	public static volatile SingularAttribute<HorasEntity, String> hora;
	public static volatile SetAttribute<HorasEntity, ReservacionesEntity> reservaciones_entity_id_horafin;
	public static volatile SingularAttribute<HorasEntity, Integer> id;

}

