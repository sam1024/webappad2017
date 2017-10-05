var day;
$("#print").on("click", function() {
    print(); 
});

$("button").on("click", function() {
	if($(this).attr('name') === 'del') {
		id = $(this).val();	
		swal({
			  title: 'AVISO!!!',
			  text: "¿ESTAS SEGURO DE CANCELAR LA RESERVACIÓN?",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Aceptar',
			  cancelButtonText: 'Cancelar'
			}).then(function () {
				if($("#h" + id).val() === "0") {
		/******** ENTRA A ESTE IF CUANDO NO SE REPITE ***********/
					cancelar(id, 0);
				} else {
		/******** ENTRA AQUÍ CUANDO SE REPITE *******************/
					var fechas_repetidas = "";
					$.ajax({
				    	async: true,
				        type: "POST",
				        url: $("#path").val() + "/reservacion/search",
				        data: {"id": $("#h" + id).val()},
				        success: function(data) {
				        	$("#fechas_repite").empty();
				        	$.each(data, function(index, element) {
				        		f = element.split("-");
				        		fechas_repetidas = fechas_repetidas + f[2] + "/" + f[1] + "/" + f[0] + " " ;
				        	});
				        	swal({
								title: "Pregunta",
								text: "La reservación se repite las fechas: " + fechas_repetidas + " ¿quieres cancelarlas todas?",
								type: 'warning',
								confirmButtonColor: '#3085d6',
								cancelButtonColor: '#d33',
								showCancelButton: true,
								confirmButtonText: "Sí",
								cancelButtonText: "No"
							}).then(function() {
								cancelar(id, 1);
							}, function(dismiss) {
								if(dismiss === 'cancel') {									
									fechas_repetidas = "";
									cancelar(id, 0);
								}
							});
				        }
				    });
				}				
			});
	} else {
		modificar($(this).val());		
	}
});

function numDayToName(number_day) {
    switch(number_day) {
        case 0:
            day = "DOMINGO";
            break;
        case 1:
            day = "LUNES";
            break;
        case 2:
            day = "MARTES";
            break;
        case 3:
            day = "MIÉRCOLES";
            break;
        case 4:
            day = "JUEVES";
            break;
        case 5:
            day = "VIERNES";
            break;
        case 6:
            day = "SÁBADO";
            break;
    }
    return day;
}

function modificar(id){
	$.ajax({
		async: true,		
		type: "POST",
		url: $("#path").val() + "/reservacion/modificar",
		data: {"id": id,}
	})
	.done(function(resers) {
		success: $("#reservaciones_x_dia").html(resers);
	});
}

function cancelar(id, no_repite) {
	$.ajax({
    	async: true,
        type: "POST",
        url: $("#path").val() + "/reservacion/cancelar",
        data: { "id": id, "no_repite": no_repite }
    });
	$("#" + id).css("display", "none");
}


