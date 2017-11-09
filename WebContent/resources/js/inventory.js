var horas;
$("#icon_menu").on("click", function() {
	hideShowMenu();
	/*$("#submenu").css("display", "none");
	$("#submenu_anidado").css("display", "none");*/
});

/*** ABRIR LA VENTANA MODAL ***/
$("#add_new").on("click", function() {
	openModal();     
});

$("#link_add_new").on("click", function() {
	hideShowMenu();
	openModal();     
});
/*** FIN ABRIR LA VENTANA MODAL ***/

/*** CERRAR LA VENTANA MODAL ***/
$("#btn_cancel").on("click", function() {
    $("#section_form").css({"bottom": "-100%", "pointer-events": "none"});
    $("#section_show_datos").css("display", "block");
});
/*** FIN CERRAR LA VENTANA MODAL ***/

/**** MOSTRA Y OCULTAR EL SUBMENU ****/
$("#li_new").on("click", function() {
	$(this).children("ul").slideToggle();
});

$("#li_search").on("click", function(e) {
	console.log("buscar");
	$(this).children("ul").slideToggle();
	e.stopPropagation();
})
/**** MOSTRA Y OCULTAR EL SUBMENU ****/

$("#search_serie").on("click", function() {
	hideShowMenu();
	$("#serie_find").css("display", "block").focus();
	$("#articulo_find").css("display", "none").focus();
	$("#recurso_find").css("display", "none").focus();
	$("#btn_show_all").css("display", "none").focus();
	$("#div_articulos").empty();
	$("#submenu").css("display", "none");
});

$("#search_articulo").on("click", function() {
	hideShowMenu();
	$("#articulo_find").css("display", "block").focus();
	$("#serie_find").css("display", "none").focus();
	$("#recurso_find").css("display", "none").focus();
	$("#btn_show_all").css("display", "none").focus();
	$("#div_articulos").empty();
	$("#submenu").css("display", "none");
});

$("#search_area").on("click", function() {
	hideShowMenu();
	$("#recurso_find").css("display", "block").focus();
	$("#articulo_find").css("display", "none").focus();
	$("#serie_find").css("display", "none").focus();
	$("#btn_show_all").css("display", "none").focus();
	$("#div_articulos").empty();
	$("#submenu").css("display", "none");
});

$("#search_activos").on("click", function() {
	hideShowMenu();
	showArticulos("", 0, 0, 1, "");
	hideInputsSearch();
	$("#submenu").css("display", "none");
});

$("#search_bajas").on("click", function() {
	hideShowMenu();
	showArticulos("", 0, 0, 0, "");
	hideInputsSearch();
	$("#submenu").css("display", "none");
});

$("#serie_find").keypress(function(e) {
	if(e.which == 13) {
		showArticulos($(this).val().toUpperCase(), 0, 0, -1, $(this).attr("id"));
	}	
});

$("#btn_save").on("click", function() {
	if($("#horas").val() === "") {
		horas = 0;
	} else {
		horas = $("#horas").val();
	}
	if(validaCampos()) {
		$.ajax( {
			type: "POST",
			url: $("#path").val() + "/inventario/save",
			data: {"id_inventario": 0, "articulo": $("#articulo").val(), "marca": $("#marca").val(), "modelo": $("#modelo").val(), "serie": $("#serie").val().toUpperCase(),
				   "recurso": $("#recurso").val(), "horas": horas, "condiciones": $("#condiciones").val().toUpperCase(),
				   "comentarios": $("#comentarios").val().toUpperCase(), "status": $("#status").val() }
		});
		$(".clean").each(function() { /*** CON ESTÁ LINEA LE DIGO QUE A CADA ELEMENTO CON LA CLASE .input_field LE PONGA UN VALOR NULO ("") ***/
			$(this).val("");
		});
		openModal();
		console.log("GUARDE!!!")
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
		showAlert("Tienes que seleccionar el artículo");
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
		console.log("UBICAION: " + $("#recurso").val());
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

function hideShowMenu() {
	$("#nav_menu").toggleClass("abrir_cerrar");
	//$("#submenu").css("display", "none");
}

function showArticulos(valor_serie, valor_articulo, valor_ubicacion, flag, name_control) {
	createCookie(valor_serie, valor_articulo, valor_ubicacion, flag, name_control);
	$.ajax({
		type: 'POST',
		url: $("#path").val() + "/inventario/articulos",
		data: {"serie": valor_serie, "id_articulo": valor_articulo, "id_ubicacion": valor_ubicacion, "flag": flag},
		success: function(articulos) {
        	$("#div_articulos").html(articulos);
        }
	});
}

function hideInputsSearch() {
	$(".hide").each(function() {
		$(this).css("display", "none");
	});
}

function createCookie(valor_serie, valor_articulo, valor_ubicacion, flag, name_control) {
	Cookies.json = true;
	var my_json, datos_cookie = {"serie": valor_serie, "id_articulo": valor_articulo, "id_ubicacion": valor_ubicacion, 
			                     "flag": flag, "name_control": name_control};
	my_json = JSON.stringify(datos_cookie);
	Cookies.set('ubicacion', my_json);
	console.log("JSON: " + my_json);
}



