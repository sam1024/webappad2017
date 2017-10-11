$("#icon_menu").on("click", function() {
    $("#nav_menu").toggleClass("abrir_cerrar");
});

/*$("#add_new").on("click", function() {
	$("#section_form").css("display", "none");
});*/

/*** ABRIR LA VENTANA MODAL ***/
$("#add_new").on("click", function() {
   openModal();     
});
/*** FIN ABRIR LA VENTANA MODAL ***/

/*** CERRAR LA VENTANA MODAL ***/
$("#btn_cancel").on("click", function() {
    $("#section_form").css({"bottom": "-33rem", "pointer-events": "none"}); 
});
/*** FIN CERRAR LA VENTANA MODAL ***/

$("#btn_save").on("click", function(e) {
	$.ajax( {
		type: "POST",
		url: $("#path").val() + "/inventario/save",
		data: {"articulo": $("#articulo").val(), "marca": $("#marca").val(), "modelo": $("#modelo").val(), "serie": $("#serie").val(),
			   "recurso": $("#recurso").val(), "horas": $("#horas").val(), "condiciones": $("#condiciones").val(),
			   "comentarios": $("#comentarios").val(), "status": $("#status").val() }
	});
	$(".input_field").each(function() { /*** CON ESTÁ LINEA LE DIGO QUE A CADA ELEMENTO CON LA CLASE .input_field LE PONGA UN VALOR NULO ("") ***/
		$(this).val("");
	});
	openModal();
});

function openModal() {
	$("#section_form").css({"bottom": 0, "pointer-events": "auto"});
}