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
    	//int id_reservacion = reservaciones_entity.getId();
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
        			if(reservaciones_entity.getId() != 0) {
        				//System.out.println("LLAMO AL METODO SAVEORUPD() ID: " + reservaciones_entity.getId());
        				reservaciones_model_interface.saveOrUpd(reservaciones_entity);
        	    	} else {
        	    		//res = "EN SERVICE SE GUARDO LA RESERVACIÓN";
        	    		reservaciones_model_interface.save(reservaciones_entity);
        	    	}
        			System.out.println("HICE RESERVACION");
        		} else {
        			System.out.println("EL: " + reservaciones_entity.getFecha() + "EL SALON ESTÁ OCUPADO");
        		}
    	}
    	return res;    	
    	
    }
    
    public List<ReservacionesEntity> findReservacionByFecha(Date fecha) {
    	return reservaciones_model_interface.findReservacionByFecha(fecha);
    }
    
    public ReservacionesEntity findReservacionById(int id) {
    	return reservaciones_model_interface.findReservacionById(id);
    }
    
    public List<ReservacionesEntity> findReservacionByIdRepetir(int id_repetir) {
    	return reservaciones_model_interface.findReservacionByIdRepetir(id_repetir);
    }
    
    public void CancelarReservacion(int id) {
    	ReservacionesEntity reservacion_entity = findReservacionById(id);    	  
  	  	reservacion_entity.setCancelada(1);
    	reservaciones_model_interface.saveOrUpd(reservacion_entity);
    }
    
    private String validadDisponibilidad(ReservacionesEntity reservaciones_entity) {
    	List<ReservacionesEntity> lst_reservaciones = new ArrayList<>();
    	String res = ""; int pos = 0;
    	/*System.out.println("ANTES DE LLAMAR EL METODO findReservacion(fecha) este es el contenido de reservacones_entity: " +
    					   reservaciones_entity.toString());*/
    	System.out.println("RESERVACIONESENTITY:\nID: " + reservaciones_entity.getId() + "\nFECHA: " + reservaciones_entity.getFecha() +
    			"\nEVENTO: " + reservaciones_entity.getEvento() + "\nRESONSABLE: " + reservaciones_entity.getResponsable() + 
    			"\nCANCELADA: " + reservaciones_entity.getCancelada() + "\nTIPO: " + reservaciones_entity.getTipo() + 
    			"\nID RECURSO: " + reservaciones_entity.getRecursos_entity().getId_recursos() + "\nID HORA INI: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas() +
    			"\nID HORA FIN: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas() + "\nID USUARIO: " + reservaciones_entity.getUsuarios_entity().getId_usuario() +
    			"\nFECHA CREACION: " + reservaciones_entity.getFecha_creacion() + "\nID REPETIR: " + reservaciones_entity.getId_repetir() + 
    			"\nID ACOMODO: " + reservaciones_entity.getAcomodos_entity().getId() + "\nPARTICIPANTES: " + reservaciones_entity.getNo_participantes() +
    			"\nREQURIMIENTOS: " + reservaciones_entity.getRequerimientos());
    	if(reservaciones_entity.getId() != 0) {
    		System.out.println("MODIFICAR!!!");
    		lst_reservaciones = reservaciones_model_interface.findReservacionByFecha(reservaciones_entity.getFecha(), reservaciones_entity.getId());
    	} else {
    		System.out.println("NUEVA!!!");
    		lst_reservaciones  = reservaciones_model_interface.findReservacionByFecha(reservaciones_entity.getFecha());
    	}
    	if(lst_reservaciones.isEmpty()) {
    		return res;
    	} else {
    		for(ReservacionesEntity lst : lst_reservaciones) {
    			System.out.println("LST: " + lst.toString());
    			System.out.println("FECHA: " + lst.getFecha() + " INICIA: " + lst.getHoras_entity_id_horaini().getId_horas() + " TERMINA: " + lst.getHoras_entity_id_horafin().getHora() +
    							   " SALON: " + lst.getRecursos_entity().getId_recursos() + " EVENTO: " + lst.getEvento() + " RESPONSABLE: " + lst.getResponsable());
    			if(lst.getRecursos_entity().getId_recursos() == reservaciones_entity.getRecursos_entity().getId_recursos()) {
    				System.out.println("\nENTRO AL IF PADRE YA QUE EL SALÓN SI APARECE EN LA LISTA DE OCUPADOS\n");
    	/************************* PRIMERA CONDICION *********************************************** 
    	******************* RESERVACION EXISTENTE QUE INICIA A LA MISMA HORA DE UNA NUEVA  ********/
    				if(lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas()) {
    					System.out.println("ID RESERVACION: " + reservaciones_entity.getId());
    					System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
    					System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
    					System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
    					System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
    					System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
    					System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
    					System.out.println("LA PRIMERA CONDICION SE CUMPLE!!");
    					res = "YA EXISTE UNA RESERVACION QUE INICIA A LAS " + lst.getHoras_entity_id_horaini().getHora();
   						return res;
    				} else { 
    	/************************* SEGUNDA CONDICION **********************************************************************************
        ******************* RESERVACION EXISTENTE QUE INICIA A LA MISMA HORA QUE TERMINA UNA NUEVA O QUE TERMINA A LA MISMA HORA
        ******************* QUE INICIA UNA NUEVA (DEJAR 1/2 HORA / EVENTOS *********/
    					if((lst.getHoras_entity_id_horaini().getId_horas() == reservaciones_entity.getHoras_entity_id_horafin().getId_horas())
    						|| (lst.getHoras_entity_id_horafin().getId_horas() == reservaciones_entity.getHoras_entity_id_horaini().getId_horas())) {
    						res = "EXISTE UNA RESERVACION DE: " + lst.getHoras_entity_id_horaini().getHora() + " A: " + lst.getHoras_entity_id_horafin().getHora() + 
      							  " DEBES DEJAR MEDIA HORA ENTRE ENVENTOS";
      						System.out.println("ID RESERVACION: " + reservaciones_entity.getId());
      						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
      						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
      						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
      						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
      						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
      						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
      						System.out.println("LA SEGUNDA CONDICION SE CUMPLE!!");
      						return res;
    					}
    	/************************* TERCERA CONDICION ********************************************************************************** 
    	******************* LA HORA DE INICIO O FIN DE UNA RESERVACION NUEVA ESTÁ EN EL RANGO DE HORAS DE UNA EXISTENTE
    	******************* O LA HORA DE INICIO DE UNA EXISTENTE ESTÁ EN EL RANGO DE HORAS DE UNA NUEVA            *******************/
    					else if ((reservaciones_entity.getHoras_entity_id_horaini().getId_horas() >= lst.getHoras_entity_id_horaini().getId_horas())
 							   && (reservaciones_entity.getHoras_entity_id_horaini().getId_horas() <= lst.getHoras_entity_id_horafin().getId_horas())
 							   || (reservaciones_entity.getHoras_entity_id_horafin().getId_horas() >= lst.getHoras_entity_id_horaini().getId_horas())
							   && (reservaciones_entity.getHoras_entity_id_horafin().getId_horas() <= lst.getHoras_entity_id_horafin().getId_horas())
							   || (lst.getHoras_entity_id_horaini().getId_horas() >= reservaciones_entity.getHoras_entity_id_horaini().getId_horas())
							   && (lst.getHoras_entity_id_horaini().getId_horas() <= reservaciones_entity.getHoras_entity_id_horafin().getId_horas())) {
    						res = "EXISTE UNA RESERVACION DE " + lst.getHoras_entity_id_horaini().getHora() + " A " + 
 							  lst.getHoras_entity_id_horafin().getHora();
    						System.out.println("ID RESERVACION: " + reservaciones_entity.getId());
    						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
    						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
    						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
    						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
    						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
    						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
    						System.out.println("LA TERCERA CONDICION (LA HORA DE INICIO O FIN DE UNA RESERVACION NUEVA ESTÁ EN EL RANGO DE HORAS DE UNA EXISTENTE\n" + 
 								"O LA HORA DE INICIO DE UNA EXISTENTE ESTÁ EN EL RANGO DE HORAS DE UNA NUEVA) SE CUMPLE!!");
    						return res;
 					} else {
    						System.out.println("HACER LA RESERVACION");
    						System.out.println("ID RESERVACION: " + reservaciones_entity.getId());
    						System.out.println("\nID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
    						System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
    						System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
    						System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
    						System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
    						System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());
    						return res;
    					}
    				}	
    			}
    			/**** ELSE FINAL  ****/ 		
    			/*else {
    				System.out.println("ID RECURSO OCUPADO: " + lst.getRecursos_entity().getId_recursos());
    				System.out.println("ID RECURSO NUEVA: " + reservaciones_entity.getRecursos_entity().getId_recursos());
    				System.out.println("ID INICIO OCUPADO: " + lst.getHoras_entity_id_horaini().getId_horas());
    				System.out.println("ID INICIO NUEVO: " + reservaciones_entity.getHoras_entity_id_horaini().getId_horas());
    				System.out.println("ID FIN OCUPADO: " + lst.getHoras_entity_id_horafin().getId_horas());
    				System.out.println("ID FIN NUEVO: " + reservaciones_entity.getHoras_entity_id_horafin().getId_horas());					
    				System.out.println("ELSE FINAL SE CUMPLE!!");
    				System.out.println("NO OCUPADO!!");*/
    				//return res;
    			//}    			
    		}/******************* FIN DEL FOR ****************/
    		return res;
    	}
    }
    
}

