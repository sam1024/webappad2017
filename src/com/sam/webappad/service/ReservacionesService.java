package com.sam.webappad.service;

import com.sam.webappad.entity.ReservacionesEntity;
import com.sam.webappad.model.ReservacionesModelInterface;

import java.sql.Date;
import java.util.ArrayList;
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
    	int id_reservacion = reservaciones_entity.getId();
    	if(!fechas.equals("")) {
    	/**** ENTRA A ESTE IF SOLO CUANDO LA RESERVACIÓN SE REPITE ****/
    	    System.out.println("Reservaciones entity: " + reservaciones_entity);
    		int id = 0;
    		ArrayList<String> lst_fechas_ocupadas = new ArrayList<>();
    		res = validadDisponibilidad(reservaciones_entity);
    		if(res.equals("")) {
    			reservaciones_model_interface.save(reservaciones_entity);
        		System.out.println("GUARDE LA PRIMERA FECHA: " + reservaciones_entity.getFecha());
        		id = reservaciones_entity.getId();
        		
        		/********* ACTUALIZO EL ID_REPETIR EN LA PRIMERA RESERVACION QUE GUARDE  *********/
        		reservaciones_entity.setId_repetir(id);
        		reservaciones_model_interface.saveOrUpd(reservaciones_entity);
        		/********* FIN ACTUALIZO EL ID_REPETIR EN LA PRIMERA RESERVACION QUE GUARDE  *********/
        		
    		} else {
    			lst_fechas_ocupadas.add(reservaciones_entity.getFecha().toString());
    			System.out.println("EL: " + reservaciones_entity.getFecha() + "EL SALON ESTÁ OCUPADO");
    		}
    		String[] arr_fechas = fechas.split("\n");    		
        	for(int i = 0; i < arr_fechas.length; i++) {
        		reservaciones_entity.setId(0);
        		reservaciones_entity.setFecha(Date.valueOf(arr_fechas[i].substring(0, 10)));
        		if(id == 0) {
        			res = validadDisponibilidad(reservaciones_entity);
            		if(res.equals("")) {
            			/**** ENTRA A ESTE IF SOLO CUANDO EL RECURSO ESTÁ DISPONIBLE ****/
            			reservaciones_model_interface.save(reservaciones_entity);
            			id = reservaciones_entity.getId();
            			/********* ACTUALIZO EL ID_REPETIR EN LA PRIMERA RESERVACION QUE GUARDE  *********/
                		reservaciones_entity.setId_repetir(id);
                		reservaciones_model_interface.saveOrUpd(reservaciones_entity);
                		/********* FIN ACTUALIZO EL ID_REPETIR EN LA PRIMERA RESERVACION QUE GUARDE  *********/
            			System.out.println("EL: " + reservaciones_entity.getFecha() + "GUARDE LA RESERVACIÓN");
            		} else {
            			lst_fechas_ocupadas.add(reservaciones_entity.getFecha().toString());	
            			System.out.println("EL: " + reservaciones_entity.getFecha() + "EL SALON ESTÁ OCUPADO");
            		}
        		} else {
        			reservaciones_entity.setId_repetir(id);
        			res = validadDisponibilidad(reservaciones_entity);
            		if(res.equals("")) {
            			/**** ENTRA A ESTE IF SOLO CUANDO EL RECURSO ESTÁ DISPONIBLE ****/
            			reservaciones_model_interface.save(reservaciones_entity);
            			System.out.println("EL: " + reservaciones_entity.getFecha() + "GUARDE LA RESERVACIÓN");
            		} else {
            			lst_fechas_ocupadas.add(reservaciones_entity.getFecha().toString());
            			System.out.println("EL: " + reservaciones_entity.getFecha() + "EL SALON ESTÁ OCUPADO");
            		}
        		}
        		if(!lst_fechas_ocupadas.isEmpty()) {
        			res = "";
        			String[] fecha_ocupada;
        			for(String fecha : lst_fechas_ocupadas) {
        				System.out.println(fecha);
    					fecha_ocupada = fecha.split("-");
        				res += fecha_ocupada[2] + "/" + fecha_ocupada[1] + "/" + fecha_ocupada[0]  + ", " ;
    				}
        			res = "PARA LAS FECHAS: " + res + " EL RECURSO QUE SOLICITASTE ESTÁ OCUPADO Y NO SE PUEDE RESERVAR";
        			System.out.println(res);
        		}
        		System.out.println("EN SERVICE: " + reservaciones_entity);
        	}        	       	   		
    	} else {
    		/**** LA RESERVACIÓN NO SE REPITE ****/
        		res = validadDisponibilidad(reservaciones_entity);
        		if(res.equals("")) {
        			if(id_reservacion != 0) {
        				reservaciones_model_interface.saveOrUpd(reservaciones_entity);
        	    	} else {
        	    		reservaciones_model_interface.save(reservaciones_entity);
        	    	}
        			System.out.println("HICE RESERVACION");
        		} else {
        			System.out.println("EL: " + reservaciones_entity.getFecha() + "EL SALON ESTÁ OCUPADO");
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
    
    public ReservacionesEntity findReservacionById(int id) {
    	return reservaciones_model_interface.findReservacionById(id);
    }
    
    private String validadDisponibilidad(ReservacionesEntity reservaciones_entity) {
    	List<ReservacionesEntity> lst_reservaciones = null;
    	String res = "";
    	lst_reservaciones  = reservaciones_model_interface.findReservacion(reservaciones_entity.getFecha());
    	if(lst_reservaciones.isEmpty()) {
    		return res;
    	} else {
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
    						//res = "HICE RESERVACION";
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
    				//res = "HICE RESERVACION";
    			}    			
    		}
    	} 
    	System.out.println("SALI DEL FOR");
    	return res;
    }
    
}

