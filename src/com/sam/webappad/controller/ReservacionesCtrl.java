package com.sam.webappad.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sam.webappad.entity.ReservacionesEntity;
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
        model.addAttribute("diames", fecha);
        model.addAttribute("lst_reservaciones", reservaciones_service.findReservacionByFecha(Date.valueOf(fecha)));
        return "reservaciones_x_dia";
    }
    
    @RequestMapping("/reservacion_new")
    public String reservacionNew(Model model) {
    	model.addAttribute("reservacion_new", new ReservacionesEntity());/*SI NO SE MANDA UNA INSTACIA DE LA CLASE
    	MARCA EL ERROR Neither BindingResult nor plain target object*/
        model.addAttribute("lst_horas", horas_service.findAll());
        model.addAttribute("lst_recursos", recursos_service.findAll());
        model.addAttribute("lst_acomodos", acomodos_service.findAll());
        return "reservacion_new";
    }
    
    @RequestMapping(value = "/reservacion/save", method = RequestMethod.POST)
    public String HandleUsuarios(@ModelAttribute("reservacion_new") ReservacionesEntity reservaciones_entity,
    							 @ModelAttribute("id_recurso") int id_recurso,@ModelAttribute("username") String user_name,
    							 @ModelAttribute("id_acomodo") int id_acomodo, @ModelAttribute("hora_inicio") int id_hora_ini,
    							 @ModelAttribute("hora_fin") int id_hora_fin, @ModelAttribute("no_participantes") String no_participantes, Model model,
    							 RedirectAttributes redirect_attributes, @ModelAttribute("fechas") String fechas) {
    	String pagina = "", res;
    	//int id_reservacion = reservaciones_entity.getId();
    	reservaciones_entity.setRecursos_entity(recursos_service.findById(id_recurso));
    	reservaciones_entity.setUsuarios_entity(usuarios_service.findByUsuario(user_name));
    	reservaciones_entity.setHoras_entity_id_horaini(horas_service.findById(id_hora_ini));
    	reservaciones_entity.setHoras_entity_id_horafin(horas_service.findById(id_hora_fin));
    	reservaciones_entity.setAcomodos_entity(acomodos_service.findById(id_acomodo));
    	res = reservaciones_service.save(reservaciones_entity, fechas);
    	System.out.println("DESPUES DE LOS SETTER A LA RESERVACION: " + reservaciones_entity);
    	if(res.equals("")) {
    		System.out.println("HECHA!!!");
    		if(reservaciones_entity.getId() == 0) {
    			System.out.println("ENTRO AL IF ID_RESERVACION == 0 ID: " + reservaciones_entity.getId());
    			/**** ENVIAR CORREO ****/
    			//    		SimpleMailMessage simple_mail_message = new SimpleMailMessage();
    			//    		simple_mail_message.setTo("samuel.arizmendi@loyola.edu.mx");
    			//    		simple_mail_message.setSubject("Nueva Reservacion");
    			//    		simple_mail_message.setText("EL USUARIO: " + usuarios_entity.getNombre_completo() + " REALIZO LA RESERVACIÓN:\n" + 
    			//    				"SALÓN: " + reservaciones_entity.getRecursos_entity().getNombre() + "\n" + "HORA: " +
    			//    				reservaciones_entity.getHoras_entity_id_horaini().getHora() + "-" + reservaciones_entity.getHoras_entity_id_horafin().getHora() +
    			//    				"\nACOMODO: " + reservaciones_entity.getAcomodos_entity().getAcomodos() + "\nPARTICIPANTES: " +
    			//    				"\nEVENTO: " + reservaciones_entity.getEvento());
    			//    		try {
    			//    			java_mail_sender.send(simple_mail_message);
    			//    			System.out.println("ENVIE CORREO");
    			/**** FIN ENVIAR CORREO ****/
    			
    			//    		} catch (Exception e) {
    			//    				System.out.println("ERROR: " + e);
    			//    			model.addAttribute("msj", "1$AL INTENTAR ENVIAR EL CORREO A LOS RESPONSABLES SE PRODUJO EL ERROR: " + e + 
    			//    					"\nFAVOR DE CONTACTAR AL AREA DE APOYO DIDÁCTICO");
    			//	pagina = "mensajes";
    			//			}
    			pagina = "reservacion_new";
    		} else {
    			System.out.println("EL ID_RESERVACION NO ES IGUAL A 0, ID: " + reservaciones_entity.getId());
    			pagina = "reservaciones";
    		}
    	} else {
    		model.addAttribute("msj", "2$"  + res);
    		System.out.println("[ReservacionesCtrl]\nERROR!!!\n" + res);
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
    	  
    	  model.addAttribute("reservacion", reservaciones_service.findReservacionById(id));
    	  model.addAttribute("lst_horas", horas_service.findAll());
          model.addAttribute("lst_recursos", recursos_service.findAll());
          model.addAttribute("lst_acomodos", acomodos_service.findAll());
    	  return "modificar";
      }
}

