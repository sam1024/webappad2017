package com.sam.webappad.model;

import com.sam.webappad.entity.UsuariosEntity;
import java.util.List;

/** @author sam **/

public interface UsuariosModel {
    
    public void save(UsuariosEntity usuarios_entity);
    public List<UsuariosEntity> findAll();
    public UsuariosEntity findById(int id);
    public List<UsuariosEntity> findByName(String name);
    public UsuariosEntity findByUsuario(String user_name);
    public void update(UsuariosEntity usuarios_entity);
    public void delete(int id);
    //public int[] saveAll(List<UsuariosEntity> lst_usuarios);
    
}
