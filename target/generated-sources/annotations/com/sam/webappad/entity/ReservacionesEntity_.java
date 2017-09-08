package com.sam.webappad.entity;

import java.sql.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ReservacionesEntity.class)
public abstract class ReservacionesEntity_ {

	public static volatile SingularAttribute<ReservacionesEntity, Integer> tipo;
	public static volatile SingularAttribute<ReservacionesEntity, String> responsable;
	public static volatile SingularAttribute<ReservacionesEntity, Integer> cancelada;
	public static volatile SingularAttribute<ReservacionesEntity, Date> fecha_creacion;
	public static volatile SingularAttribute<ReservacionesEntity, UsuariosEntity> usuarios_entity;
	public static volatile SingularAttribute<ReservacionesEntity, RecursosEntity> recursos_entity;
	public static volatile SingularAttribute<ReservacionesEntity, Date> fecha;
	public static volatile SingularAttribute<ReservacionesEntity, String> evento;
	public static volatile SingularAttribute<ReservacionesEntity, HorasEntity> horas_entity_id_horafin;
	public static volatile SingularAttribute<ReservacionesEntity, Integer> id_repetir;
	public static volatile SingularAttribute<ReservacionesEntity, Integer> id;
	public static volatile SingularAttribute<ReservacionesEntity, HorasEntity> horas_entity_id_horaini;
	public static volatile SingularAttribute<ReservacionesEntity, AcomodosEntity> acomodos_entity;

}

