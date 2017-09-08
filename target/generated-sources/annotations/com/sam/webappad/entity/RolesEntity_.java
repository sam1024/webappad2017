package com.sam.webappad.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(RolesEntity.class)
public abstract class RolesEntity_ {

	public static volatile SingularAttribute<RolesEntity, String> role;
	public static volatile SingularAttribute<RolesEntity, Integer> id_roles;
	public static volatile SetAttribute<RolesEntity, UsuariosEntity> usuarios_entitys;

}

