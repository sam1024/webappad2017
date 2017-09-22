package com.sam.webappad.controller;

import com.sam.webappad.entity.AcomodosEntity;
import com.sam.webappad.entity.HorasEntity;
import com.sam.webappad.entity.RecursosEntity;
import com.sam.webappad.entity.ReservacionesEntity;
import com.sam.webappad.entity.UsuariosEntity;
import com.sam.webappad.service.AcomodosService;
import com.sam.webappad.service.HorasService;
import com.sam.webappad.service.RecursosService;
import com.sam.webappad.service.ReservacionesService;
import com.sam.webappad.service.UsuariosService;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    
    private RecursosEntity recursos_entity;
	private UsuariosEntity usuarios_entity;
	private HorasEntity horas_entity_ini;
	private HorasEntity horas_entity_fin;
	private AcomodosEntity acomodos_entity;
    
    @RequestMapping("/reservaciones")
    public String showReservaciones() {
        return "reservaciones";
    }
    
    @RequestMapping(value = "/xdia", method = RequestMethod.POST)
    public String showXDia(Model model, @ModelAttribute("fecha") String fecha) {
        model.addAttribute("diames", fecha);
        model.addAttribute("lst_reservaciones", reservaciones_service.findByFecha(Date.valueOf(fecha)));
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
    	String pagina = "", res, evento;
    	if(!no_participantes.equals("")) {
    		int numero = Integer.parseInt(no_participantes);
    		if(numero > 0) {
    			evento  = no_participantes + " PARTICIPANTES<br />" + reservaciones_entity.getEvento();
    			System.out.println("EVENTO: " + evento);
    			reservaciones_entity.setEvento(evento);
    		}
    	}
    	recursos_entity = recursos_service.findById(id_recurso);
    	usuarios_entity = usuarios_service.findByUsuario(user_name);
    	horas_entity_ini = horas_service.findById(id_hora_ini);
    	horas_entity_fin = horas_service.findById(id_hora_fin);
    	acomodos_entity = acomodos_service.findById(id_acomodo);
    	reservaciones_entity.setRecursos_entity(recursos_entity);
    	reservaciones_entity.setUsuarios_entity(usuarios_entity);
    	reservaciones_entity.setHoras_entity_id_horaini(horas_entity_ini);
    	reservaciones_entity.setHoras_entity_id_horafin(horas_entity_fin);
    	reservaciones_entity.setAcomodos_entity(acomodos_entity);
    	res = reservaciones_service.save(reservaciones_entity, fechas); 
    	if(res.equals("")) {
    		System.out.println("HECHA!!!");
    		
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
//    			/**** FIN ENVIAR CORREO ****/
    			pagina = "reservacion_new";
//    		} catch (Exception e) {
//    			System.out.println("ERROR: " + e);
//    			model.addAttribute("msj", "1$AL INTENTAR ENVIAR EL CORREO A LOS RESPONSABLES SE PRODUJO EL ERROR: " + e + 
//    					"\nFAVOR DE CONTACTAR AL AREA DE APOYO DIDÁCTICO");
//				pagina = "mensajes";
//			}
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
    	  System.out.println("RESERVACION: " + reservaciones_service.findReservacionById(id));
    	  model.addAttribute("reservacion", reservaciones_service.findReservacionById(id));
    	  model.addAttribute("lst_horas", horas_service.findAll());
          model.addAttribute("lst_recursos", recursos_service.findAll());
          model.addAttribute("lst_acomodos", acomodos_service.findAll());
    	  return "modificar";
      }
}

