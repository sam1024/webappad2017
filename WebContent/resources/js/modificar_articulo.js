var id_inventario;

$("#btn_save_upd").on("click", function() {
	if($("#id_inventario").val() === "") {
		id_inventario = 0;
	} else {
		id_inventario = $("#id_inventario").val();
	}
	console.log(id_inventario);
	$.ajax({
		type: "POST",
		url: $("#path").val() + "/inventario/save",
		data:{"id_inventario": id_inventario, "articulo": $("#id_articulo").val(), "marca": $("#id_marca").val(), "modelo": $("#id_modelo").val(),
			"serie": $("#serie").val().toUpperCase(), "recurso": $("#ubicacion").val(), "horas": $("#horas").val(), "condiciones": $("#condiciones").val().toUpperCase(),
			"comentarios": $("#comentarios").val().toUpperCase(), "status": $("#status").val()}
	});
	backToPage();
});

$("#btn_cancel_upd").on("click", function() {
	backToPage();
});

function backToPage() {
	j = Cookies.get('ubicacion');
	if(j["name_control"] !== "") {
		$("#" + j["name_control"]).css("display", "block");
	}
	showArticulos(j["serie"], j["id_articulo"], j["id_ubicacion"], j["flag"], "");
}
