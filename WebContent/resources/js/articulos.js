$("button").on("click", function() {
	$("#recurso_find").css("display", "none");
	$("#articulo_find").css("display", "none");
	$("#serie_find").css("display", "none");
	$("#btn_show_all").css("display", "none");
	$("#add_new").css("display", "none");
	$("#div_articulos").empty();
	if($(this).attr("name") === "edit") {
		$.ajax({
			type: 'POST',
			url: $("#path").val() + "/inventario/modificar",
			data: {"id": $(this).val()},
			success: function(modificar) {
	        	$("#div_articulos").html(modificar);
	        } 
		});
	} else {
		console.log("dar de baja");
	}
	
});