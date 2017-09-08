package com.sam.webappad.entity;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/** @author sam **/

@Entity
@Table(name = "ROLES")
public class RolesEntity {
    
    @Id
    @Column(name = "id")
    private int id_roles;
    private String role;
    
    @OneToMany(mappedBy = "roles_entity")
    private Set<UsuariosEntity> usuarios_entitys;

    public RolesEntity() {
    }

    public RolesEntity(String role) {
        this.role = role;
    }

    public int getId_roles() {
        return id_roles;
    }

    public void setId_roles(int id_roles) {
        this.id_roles = id_roles;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<UsuariosEntity> getUsuarios_entitys() {
        return usuarios_entitys;
    }

    public void setUsuarios_entitys(Set<UsuariosEntity> usuarios_entitys) {
        this.usuarios_entitys = usuarios_entitys;
    }
    
}
