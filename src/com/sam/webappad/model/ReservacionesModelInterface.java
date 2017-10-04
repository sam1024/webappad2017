package com.sam.webappad.model;

import com.sam.webappad.entity.ReservacionesEntity;
import java.sql.Date;
import java.util.List;

/** @author sam **/

public interface ReservacionesModelInterface {
    
    public void save(ReservacionesEntity reservaciones_entity);
    public void saveOrUpd(ReservacionesEntity reservaciones_entity);
    public ReservacionesEntity findReservacionById(int id);
    public List<ReservacionesEntity> findReservacionByIdRepetir(int id_repetir);
    public List<ReservacionesEntity> findReservacionByFecha(Date fecha);
	public List<ReservacionesEntity> findReservacionByFecha(Date fecha, int id_reservacion);
    
    
}
