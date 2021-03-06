package com.sam.webappad.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sam.webappad.entity.ReservacionesEntity;
import com.sam.webappad.entity.UsuariosEntity;
import com.sam.webappad.service.AcomodosService;
import com.sam.webappad.service.HorasService;
import com.sam.webappad.service.RecursosService;
import com.sam.webappad.service.ReservacionesService;
import com.sam.webappad.service.UsuariosService;

/** @author sam **/

@Controller
@SessionAttributes("msj")
public class ReservacionesCtrl {
	
	@Autowired
	private JavaMailSender java_mail_sender;
    
    @Autowired
    private HorasService horas_service;
    
    @Autowired
    private RecursosService recursos_service;
    
    @Autowired
    private ReservacionesService reservaciones_service;
    
    @Autowired
    private UsuariosService usuarios_service;
    
    @Autowired
    private AcomodosService acomodos_service;
    
    @RequestMapping("/reservaciones")
    public String showReservaciones() {
        return "reservaciones";
    }
    
    @RequestMapping(value = "/xdia", method = RequestMethod.POST)
    public String showXDia(Model model, @ModelAttribute("fecha") String fecha) {
    	String fecha_formateada[] = fecha.split("-");
    	model.addAttribute("diames", fecha_formateada[2] + "/" + fecha_formateada[1] + "/" + fecha_formateada[0]);
        model.addAttribute("lst_reservaciones", reservaciones_service.findReservacionByFecha(Date.valueOf(fecha)));
        return "reservaciones_x_dia";
    }
    
    @RequestMapping("/reservacion_new")
    public String reservacionNew(Model model) {
    	model.addAttribute("reservacion_new", new ReservacionesEntity());/*SI NO SE MANDA UNA INSTACIA DE LA CLASE
    	MARCA EL ERROR Neither BindingResult nor plain target object*/
        model.addAttribute("lst_horas", horas_service.findAll());
        model.addAttribute("lst_recursos", recursos_service.findAll());
        //model.addAttribute("lst_acomodos", acomodos_service.findAll());
        return "reservacion_new";
    }
    
    @RequestMapping(value = "/reservacion/save", method = RequestMethod.POST)
    public String HandleUsuarios(@ModelAttribute("reservacion_new") ReservacionesEntity reservaciones_entity,
    							 @ModelAttribute("id_recurso") int id_recurso,@ModelAttribute("username") String user_name,
    							 @ModelAttribute("hora_inicio") int id_hora_ini, @ModelAttribute("hora_fin") int id_hora_fin, 
    							 Model model, RedirectAttributes redirect_attributes, @ModelAttribute("fechas") String fechas) {
    	String pagina = "", res, fecha_reservacion = String.valueOf(reservaciones_entity.getFecha());
    	int id_reservacion = reservaciones_entity.getId();
    	UsuariosEntity usuarios_entity = usuarios_service.findByUsuario(user_name);
    	reservaciones_entity.setRecursos_entity(recursos_service.findById(id_recurso));
    	reservaciones_entity.setUsuarios_entity(usuarios_entity);
    	reservaciones_entity.setHoras_entity_id_horaini(horas_service.findById(id_hora_ini));
    	reservaciones_entity.setHoras_entity_id_horafin(horas_service.findById(id_hora_fin));
    	reservaciones_entity.setAcomodos_entity(acomodos_service.findById(1));
    	System.out.println("RESERVACION: " + reservaciones_entity);
    	res = reservaciones_service.save(reservaciones_entity, fechas);
    	if(res.equals("")) {
    		/**************************************** ENVIAR CORREO ******************************************************/
    		if(id_reservacion == 0) {
    			//String[] destinatarios = {"samuel.arizmendi@loyola.edu.mx", "sam.abundis82@gmail.com"};
    			String[] destinatarios = {"samuel.hernandez@loyola.edu.mx", "beatriz.tamariz@loyola.edu.mx"};
    			String msj_mail = usuarios_entity.getNombre_completo() + " REALIZO LA RESERVACIÓN:" + 
    					   "\nFECHA: " + fecha_reservacion + 
	    	    		   "\nSALÓN: " + reservaciones_entity.getRecursos_entity().getNombre() + "\n" + "HORA: " +
	    	    		   reservaciones_entity.getHoras_entity_id_horaini().getHora() + "-" + reservaciones_entity.getHoras_entity_id_horafin().getHora() +
	    	    		   "\nEVENTO: " + reservaciones_entity.getEvento();
    			if(!fechas.equals("")) {
    				String fecha = "";
    				for(ReservacionesEntity lst_temp : reservaciones_service.findReservacionByIdRepetir(reservaciones_entity.getId_repetir())) {
    					fecha = fecha + String.valueOf(lst_temp.getFecha());    					
    				}
    				msj_mail = msj_mail + "\nLA RESERVACION SE REPITE LAS FECHAS: " + fecha + " ";
    			}
    			SimpleMailMessage simple_mail_message = new SimpleMailMessage(); 
    			simple_mail_message.setTo(destinatarios);
    			simple_mail_message.setSubject("Nueva Reservación");
    			simple_mail_message.setText(msj_mail);
    			try {
    				java_mail_sender.send(simple_mail_message);
    			} catch (Exception e) {
    				model.addAttribute("msj", "1$AL INTENTAR ENVIAR EL CORREO A LOS RESPONSABLES SE PRODUJO EL ERROR: " + e + 
    			    				   "\nFAVOR DE CONTACTAR AL AREA DE APOYO DIDÁCTICO");
    				pagina = "mensajes";
    			}
    			pagina = "reservacion_new";
    		/************************************* FIN ENVIAR CORREO ******************************************************/
    		} else {
    			pagina = "reservaciones";
    		}
    	} else {
    		model.addAttribute("msj", "2$"  + res);
    		pagina = "mensajes";
    	}
    	return "redirect:/" + pagina;
    }
        
      @RequestMapping("/mensajes")
      public String mensajes() {
    	  return "mensajes";
      }
      
      @RequestMapping(value = "/reservacion/modificar", method = RequestMethod.POST)
      public String Modificar(@ModelAttribute("id") int id, Model model) {
    	  model.addAttribute("reservacion_new", new ReservacionesEntity());/*SI NO SE MANDA UNA INSTACIA DE LA CLASE
      	  MARCA EL ERROR Neither BindingResult nor plain target object*/
    	  ReservacionesEntity reservaciones_entity = reservaciones_service.findReservacionById(id);
    	  //model.addAttribute("reservacion", reservaciones_service.findReservacionById(id));
    	  model.addAttribute("reservacion", reservaciones_entity);
    	  model.addAttribute("lst_horas", horas_service.findAll());
          model.addAttribute("lst_recursos", recursos_service.findAll());
          //model.addAttribute("lst_acomodos", acomodos_service.findAll());
          if(reservaciones_entity.getId_repetir() != 0) {
        	  model.addAttribute("lst_repetidas", reservaciones_service.findReservacionByIdRepetir(reservaciones_entity.getId_repetir())); //@ModelAttribute("id_repetir") int id_repetir,
          }
    	  return "modificar";
      }
      
      @RequestMapping(value = "/reservacion/cancelar", method = RequestMethod.POST)
      public String cancelar(@ModelAttribute("id") int id, @ModelAttribute("no_repite") int no_repite) {    	  
    	  reservaciones_service.CancelarReservacion(id, no_repite);
    	  return "reservaciones_x_dia";
      }
      
/***************************** REGRESA DATOS EN FORMATO JSON *********************************************/
      @RequestMapping(value = "/reservacion/search", produces = "application/json")/**ES MUY IMPORTANTE QUE LE PONGAMOS application/json
      PARA QUE EL CONTROLADOR NO NOS RETORNE UNA VISTA O UNA REDIRECCIÓN, PERO SÍ UNA CADENA JSON **/
      @ResponseBody
      public Map<String, Object> findReservacionToCancel(@RequestParam("id") int id) {
    	  Map<String, Object> map = new HashMap<>();
    	  List<ReservacionesEntity> lst_cancel = reservaciones_service.findReservacionByIdRepetir(id);    	  
    	  for(int i = 0; i < lst_cancel.size(); i++) {
    		  ReservacionesEntity reservaciones_entity = lst_cancel.get(i);
    		  map.put("fecha" + (i + 1), reservaciones_entity.getFecha());
    	  }
    	  System.out.println("RESERVACION/SEARCH: " + map.toString());
    	  return map;
      }
/***************************** FIN REGRESA DATOS EN FORMATO JSON *********************************************/
}



