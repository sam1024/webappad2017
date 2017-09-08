date = new Date();

$("#icon_menu").on("click", function() {
    $("#nav_menu").toggleClass("abrir_cerrar");
});

$("#fecha").pickadate( {
    format: "yyyy-mm-dd",
    monthsFull: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre',
                 'Noviembre', 'Deciembre']
});

$("#form_new_reservacion").submit(function(e) {
	validaCampos(e);
});

$("#btn_repeat").on("click", function(e) {
	validaCampos();
	$("#actividades-modal").css({"top": 0, "pointer-events": "auto"});
	e.preventDefault();	
});

$("#btn_clean").on("click", function() {
	location.reload();
});

$("#fecha_creacion").val(date.getFullYear() + "-" + monthLessTen((date.getMonth() + 1)) + "-" + dayLessTen(date.getDate()));

function validaCampos(e) {
	if($("#fecha").val() === "") {
		showAlert("Elcampo fecha no puede ser vacio");
		e.preventDefault();
	} else {		
		hoy = date.getFullYear() + "-" + monthLessTen((date.getMonth() + 1)) + "-" + dayLessTen(date.getDate());
		console.log("HOY: " + hoy + " Fecha: " + $("#fecha").val());
		console.log("Fecha: " + new Date($("#fecha").val()).getTime() + " Hoy: " + new Date(hoy).getTime());
		if(new Date($("#fecha").val()).getTime() < (new Date(hoy).getTime())) {
			showAlert("No puedes hacer una reservacion para una fecha anterior a hoy");
			e.preventDefault();
		} else if($("#hora_inicio").val() === "") {
			showAlert("El campo hora inicia no puede ser vacio");
			e.preventDefault();
		} else if($("#hora_fin").val() === "") {
			showAlert("El campo hora termina no puede ser vacio");		
			e.preventDefault();
		} else {
			if(parseInt($("#hora_inicio").val()) == parseInt($("#hora_fin").val())) {
				showAlert("La hora de termino no puede ser igual a la de inicio");
				e.preventDefault();
			} else if(parseInt($("#hora_inicio").val()) > parseInt($("#hora_fin").val())) {
				showAlert("La hora de termino no puede ser menor a la de inicio");
				e.preventDefault();
			} else if($("#recurso").val() === "") {
				showAlert("El campo salón no puede ser vacio");
				e.preventDefault();
			} else if($("#acomodo").val() === "") {
				showAlert("El campo acomodo no puede ser vacio");
				e.preventDefault();
			} else if($("#evento").val() === "") {
				showAlert("El campo evento no puede ser vacio");
				e.preventDefault();
			} else if($("#responsable").val() === "") {
				showAlert("El campo responsable no puede ser vacio");
				e.preventDefault();
			}
		}		
	} 
}

function showAlert(msj) {
	swal('ERROR!!!',
			 msj,
			 'error');
}

function dayLessTen(dia) {
	dia < 10 ? dia = "0" + dia : dia = dia;
	return dia;
    
}

function monthLessTen(mes) {
	mes < 10 ? mes = "0" + mes : mes = mes;
	return mes;
}

function showMsjDialog(id) {
	if(id === 1) {
		swal(
			 "ERROR",
			 "El salon está ocupado a esa hora " + id,
			 "error");
	}
	//$(location).attr('href', history.back());
	
}

//function repetir(fecha, evento, responsable, cancelada, tipo, id_recurso, id_hora_ini, id_hora_fin, usuario,
//		         fecha_creacion) {
//	console.log("fecha" + fecha +
//        	   " evento" + evento +
//        	   " responsable" + responsable +
//        	   " cancelada" + cancelada +
//        	   " tipo" + tipo +
//        	   " id_recurso" + id_recurso +
//        	   " id_hora_ini" + id_hora_ini +
//        	   " id_hora_fin" + id_hora_fin +
//        	   " usuario" + usuario +
//        	   " fecha_creacion" + fecha_creacion);
//	$.ajax({
//        type: "POST",
//        url: $("#path").val() + "/repetir",
//        data: {
//        	   "fecha": fecha,
//        	   "evento": evento,
//        	   "responsable": responsable,
//        	   "cancelada": cancelada,
//        	   "tipo": tipo,
//        	   "id_recurso": id_recurso,
//        	   "id_hora_ini": id_hora_ini,
//        	   "id_hora_fin": id_hora_fin,
//        	   "usuario": usuario,
//        	   "fecha_creacion": fecha_creacion
//        	  }
//    });
////    .done(function(resers) {
////        success: $("#reservaciones_x_dia").html(resers);
////    });
//}