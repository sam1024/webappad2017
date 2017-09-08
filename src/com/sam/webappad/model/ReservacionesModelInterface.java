package com.sam.webappad.model;

import com.sam.webappad.entity.ReservacionesEntity;
import java.sql.Date;
import java.util.List;

/** @author sam **/

public interface ReservacionesModelInterface {
    
    public void save(ReservacionesEntity reservaciones_entity);
    public void saveOrUpd(ReservacionesEntity reservaciones_entity);
//    public ReservacionesEntity findReservacion(int id_horaini, int id_horafin, Date fecha, int id_salon);
    public List<ReservacionesEntity> findByFecha(Date fecha);
    public List<ReservacionesEntity> findReservacion(Date fecha);
    
}
