$("#btn_save_upd").on("click", function() {
	console.log("GUARDAR!!")
	$.ajax({
		type: "POST",
		url: $("#path") + "/inventario/save",
		data:{"articulo": $("#id_articulo").val(), "marca": $("#id_marca").val(), "modelo": $("#id_modelo").val(),
			"serie": $("#serie").val(), "recurso": $("#ubicacion").val(), "horas": $("#horas").val(), "condiciones": $("#condiciones").val(),
			"comentarios": $("#comentarios").val(), "status": $("#status").val()}
	});
});

$("#btn_cancel_upd").on("click", function() {
	j = Cookies.get('ubicacion');
	console.log("CANCELAR!!ss");
	if(j["name_control"] !== "") {
		$("#" + j["name_control"]).css("display", "block");
	}		
	console.log("SERIE: " + j["serie"]);
	console.log("ID ARTICULO: " + j["id_articulo"]);
	console.log("ID UBICACION: " + j["id_ubicacion"]);
	console.log("FLAG: " + j["flag"]);
	console.log("CONTROL: " + j["name_control"]);
	showArticulos(j["serie"], j["id_articulo"], j["id_ubicacion"], j["flag"], "");	
});

