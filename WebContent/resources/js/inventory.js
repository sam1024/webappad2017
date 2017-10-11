$("#icon_menu").on("click", function() {
    $("#nav_menu").toggleClass("abrir_cerrar");
});

$(function() {
	$("#li_new").append("<a href='javascript:void(0)' id='search'><i class='fa fa-search' title='Buscar'><p class='p_menu'>Buscar</p></i></a>");
});

/*** ABRIR LA VENTANA MODAL ***/
$("#add_new").on("click", function() {
   openModal();     
});
/*** FIN ABRIR LA VENTANA MODAL ***/

/*** CERRAR LA VENTANA MODAL ***/
$("#btn_cancel").on("click", function() {
    $("#section_form").css({"bottom": "-100%", "pointer-events": "none"});
    $("#section_show_datos").css("display", "block");
});
/*** FIN CERRAR LA VENTANA MODAL ***/

$("#btn_save").on("click", function() {
	if(validaCampos()) {
		$.ajax( {
			type: "POST",
			url: $("#path").val() + "/inventario/save",
			data: {"articulo": $("#articulo").val().toUpperCase(), "marca": $("#marca").val(), "modelo": $("#modelo").val(), "serie": $("#serie").val().toUpperCase(),
				   "recurso": $("#recurso").val(), "horas": $("#horas").val(), "condiciones": $("#condiciones").val().toUpperCase(),
				   "comentarios": $("#comentarios").val().toUpperCase(), "status": $("#status").val() }
		});
		$(".input_field").each(function() { /*** CON ESTÁ LINEA LE DIGO QUE A CADA ELEMENTO CON LA CLASE .input_field LE PONGA UN VALOR NULO ("") ***/
			$(this).val("");
		});
		openModal();
	}
});

function openModal() {
	$("#articulo").focus();
	$("#section_form").css({"bottom": 0, "pointer-events": "auto"});
	$("#section_show_datos").css("display", "none");
}

function validaCampos() {
	if($("#articulo").val() === "") {
		$("#articulo").focus();
		showAlert("Tienes que introducir el nombre del artículo");
		return false;		
	} else if($("#marca").val() === "") {
		$("#marca").focus();
		showAlert("Tienes que seleccionar una marca");		
		return false;
	} else if($("#modelo").val() === "") {
		$("#modelo").focus();
		showAlert("Tienes que seleccionar un modelo");
		return false;
	} else if($("#serie").val() === "") {
		$("#serie").focus();
		showAlert("Tienes que introducir el numero de serie");
		return false;
	} else if($("#recurso").val() === "") {
		$("#recurso").focus();
		showAlert("Tienes que seleccionar la ubicación");
		return false;
	} else if($("#condiciones").val() === "") {
		$("#condiciones").focus();
		showAlert("Tienes que introducir en que condición se encuentra el artículo");
		return false;
	} else {
		return true;
	}
}

function showAlert(msj) {
	swal(
		"AVISO!!",
		msj,
		"warning"
	)
}

function addMenuElement() {
		
}



