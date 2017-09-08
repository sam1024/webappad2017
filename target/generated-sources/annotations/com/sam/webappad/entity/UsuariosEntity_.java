package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(UsuariosEntity.class)
public abstract class UsuariosEntity_ {

	public static volatile SingularAttribute<UsuariosEntity, RolesEntity> roles_entity;
	public static volatile SingularAttribute<UsuariosEntity, Integer> id_rol;
	public static volatile SingularAttribute<UsuariosEntity, String> nombre_completo;
	public static volatile SingularAttribute<UsuariosEntity, String> pass;
	public static volatile SingularAttribute<UsuariosEntity, String> usuario;
	public static volatile SetAttribute<UsuariosEntity, ReservacionesEntity> reservaciones_entity;
	public static volatile SingularAttribute<UsuariosEntity, Integer> id;

}

