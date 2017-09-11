package com.sam.webappad.service;

import com.sam.webappad.entity.ReservacionesEntity;
import com.sam.webappad.model.ReservacionesModelInterface;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** @author sam **/

@Service
public class ReservacionesService {
    
    @Autowired
    private ReservacionesModelInterface reservaciones_model_interface;
    
    public String save(ReservacionesEntity reservaciones_entity, String fechas) {
    	String res = "";
    	List<ReservacionesEntity> lst_reservaciones = reservaciones_model_interface.findReservacion(reservaciones_entity.getFecha());
    	System.out.println("Tam: " + lst_reservaciones.size());
    	System.out.println("Reservaciones entity: " + reservaciones_entity);
    	
    	if(!fechas.equals("")) {
    		int id = 0;
        	id = reservaciones_entity.getId();
    		String[] arr_fechas = fechas.split("\n");
    		for(int i = 0; i < arr_fechas.length; i++) {
    			System.out.println("[" + arr_fechas[i].substring(0, 10) + "]");
    			reservaciones_entity.setId(0);
    			reservaciones_entity.setFecha(Date.valueOf(arr_fechas[i].substring(0, 10)));
    			reservaciones_entity.setId_repetir(id);
//    			reservaciones_model_interface.save(reservaciones_entity);
    			System.out.println("EN SERVICE: " + reservaciones_entity);
    		}    		
    	} else {
    		if(lst_reservaciones.size() == 0) {
        		reservaciones_model_interface.save(reservaciones_entity);
        	} else {
        		res = validadDisponibilidad(lst_reservaciones, reservaciones_entity);
        		if(res.equals("")) {
        			//reservaciones_model_interface.save(reservaciones_entity);
        			System.out.println("HICE RESERVACION");
        		} else {
        			System.out.println("[ReservacionesService]\n" + res);
        		}
        	}
    	}
    	return res;    	
    	
    }
    
    public List<ReservacionesEntity> findByFecha(Date fecha) {
    	return reservaciones_model_interface.findByFecha(fecha);
    }
    
    public List<ReservacionesEntity> findReservacion(Date fecha) {
    	return reservaciones_model_interface.findReservacion(fecha);
    }
    
    private String validadDisponibilidad(List<ReservacionesEntity> lst_reservaciones, ReservacionesEntity reservaciones_entity) {
    	String res = "";
    	for(ReservacionesEntity lst : lst_reservaciones) {
			System.out.println("FECHA: " + lst.getFecha() + " INICIA: " + lst.getHoras_entity_id_horaini().getId_horas() + " TERMINA: " + lst.getHoras_entity_id_horafin().getHora() +
							   " SALON: " + lst.getRecursos_entity().getId_recursos() + " EVENTO: " + lst.getEvento() + " RESPONSABLE: " + lst.getResponsable());
			
			if(lst.getRecursos_entity().getId_recursos() == reservaciones_entity.getRecursos_entity().getId_recursos()) {
				
				System.out.println("\nENTRO AL IF PADRE YA QUE EL SALÓN SI APARECE EN LA LISTA DE OCUPADOS\n");
				
				/****** PRIMERA CONDICION *****/
				if(lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas()) {
					res = "YA EXISTE UNA RESERVACION QUE INICIA A LAS " + lst.getHoras_entity_id_horaini().getHora();
					System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
					System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
					System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
					System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
					System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
					System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
					System.out.println("LA PRIMERA CONDICION SE CUMPLE!!");
				} else { /****** SEGUNDA CONDICION *****/
					if(lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horafin().getId_horas()) {
						res = "EXISTE UNA RESERVACION QUE INICIA A LAS " + lst.getHoras_entity_id_horaini().getHora() + 
							  "DEBES DEJAR MEDIA HORA ENTRE ENVENTOS";
						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
						System.out.println("LA SEGUNDA CONDICION SE CUMPLE!!");
					} /****** TERCERA CONDICION *****/ 
					else if(lst.getHoras_entity_id_horafin().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas()) {
						res = "EXISTE UNA RESERVACION QUE TERMINA A LAS " + lst.getHoras_entity_id_horaini().getHora() + 
							  "DEBES DEJAR MEDIA HORA ENTRE ENVENTOS";
						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
						System.out.println("LA TERCERA CONDICION SE CUMPLE!!");
					}  /****** CUARTA CONDICION *****/ 
					else if ((reservaciones_entity.getHoras_entity_id_horafin().getId_horas() > lst.getHoras_entity_id_horaini().getId_horas())
							   && (reservaciones_entity.getHoras_entity_id_horafin().getId_horas() <= lst.getHoras_entity_id_horafin().getId_horas())) {
						res = "EXISTE UNA RESERVACION DE " + lst.getHoras_entity_id_horaini().getHora() + " A " + 
							  lst.getHoras_entity_id_horafin().getHora();
						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
						System.out.println("LA CUARTA CONDICION SE CUMPLE!!");
					}   /****** QUINTA CONDICION *****/
					else if ((reservaciones_entity.getHoras_entity_id_horaini().getId_horas() >= lst.getHoras_entity_id_horaini().getId_horas())
							   && (reservaciones_entity.getHoras_entity_id_horaini().getId_horas() <= lst.getHoras_entity_id_horafin().getId_horas())) {
						res = "EXISTE UNA RESERVACION DE " + lst.getHoras_entity_id_horaini().getHora() + " A " + 
							  lst.getHoras_entity_id_horafin().getHora();
						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
						System.out.println("LA QUINTA CONDICION SE CUMPLE!!");
					} else {
						System.out.println("HACER LA RESERVACION");
						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
						//System.out.println("LA SEGUNDA CONDICION SE CUMPLE!!");
						res = "HICE RESERVACION";
					}
				}	
			}
			/**** ELSE FINAL  ****/ 		
			else {
				System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
				System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
				System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
				System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
				System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
				System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());					
				System.out.println("ELSE FINAL SE CUMPLE!!");
				System.out.println("NO OCUPADO!!");
				res = "HICE RESERVACION";
			}    			
		}
    	return res;
    }
    
}

///**** PRIMERA CONDICION  QUE LA NUEVA RESERVACION SEA EXACTAMENTE A L MISMA HORA DE INICIO Y FIN DE UNA EXISTENTE ****/
/////*if((lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
////  && (lst.getHoras_entity_id_horafin().getId_horas() == reservaciones_entity.getHoras_entity_id_horafin().getId_horas())) { 
//			System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
//			System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
//			System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("LA PRIMERA CONDICION SE CUMPLE!!");
//			res = "PARA EL " + reservaciones_entity.getFecha() + " EL: " + reservaciones_entity.getRecursos_entity().getNombre() +
//			      " ESTÁ OCUPADO DE: " + lst.getHoras_entity_id_horaini().getHora() + " A: " +
//				  lst.getHoras_entity_id_horafin().getHora();
//		//}
//		/**** SEGUNDA CONDICION QUE LA NUEVA RESERVACION EMPIEZE EXACTAMENTE CUANDO UNA EXISTENTE TERMINE
//		 *    O TERMINE EXACTAMENTE CUANDO UN EXISTENTE EMPIEZE (DEJAR 1/2 HORA ENTRE EVENTOS)  ****/
////		else if((lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
////		   || (lst.getHoras_entity_id_horafin().getId_horas() == reservaciones_entity.getHoras_entity_id_horafin().getId_horas())
////		   || (lst.getHoras_entity_id_horafin().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
////		   || (lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horafin().getId_horas())) {
//		else if((lst.getRecursos_entity().getId_recursos() == reservaciones_entity.getRecursos_entity().getId_recursos())
//				  && (lst.getHoras_entity_id_horaini().getId_horas() < reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
//				  && (lst.getHoras_entity_id_horafin().getId_horas() < reservaciones_entity.getHoras_entity_id_horafin().getId_horas()) ) {
//			System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
//			System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
//			System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("LA SEGUNDA CONDICION SE CUMPLE!!");
//			res = "PARA EL " + reservaciones_entity.getFecha() + " EL: " + reservaciones_entity.getRecursos_entity().getNombre() +
//			      " ESTÁ OCUPADO DE: " + lst.getHoras_entity_id_horaini().getHora() + " A: " +
//				  lst.getHoras_entity_id_horafin().getHora() + "\n DEBES DEJAR MEDIA HORA ENTRE EVENTOS";
//		
//						/**** TERCERA CONDICION  ****/
//		} else if((lst.getRecursos_entity().getId_recursos() == reservaciones_entity.getRecursos_entity().getId_recursos())
//				  && (lst.getHoras_entity_id_horaini().getId_horas() > reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
//				  && (lst.getHoras_entity_id_horafin().getId_horas() > reservaciones_entity.getHoras_entity_id_horafin().getId_horas())) {
//			System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
//			System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
//			System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("LA TERCERA CONDICION SE CUMPLE!!");
//			System.out.println("HACER RESERVACION");
//			res = "";				
//		}		/**** CUARTA CONDICION  ****/ 
//		else if((lst.getHoras_entity_id_horaini().getId_horas() < reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
//				&& (lst.getHoras_entity_id_horafin().getId_horas() > reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
//				&& (lst.getHoras_entity_id_horafin().getId_horas() < reservaciones_entity.getHoras_entity_id_horafin().getId_horas()) ) {
//			System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
//			System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
//			System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
//			System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
//			System.out.println("LA CUARTA CONDICION SE CUMPLE!!");
//			System.out.println("HACER RESERVACION");
//			res = "YA EXISTE UNA DE: " + lst.getHoras_entity_id_horaini().getHora() + " A: " + lst.getHoras_entity_id_horafin().getHora();				
//		}

