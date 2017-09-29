var day;
$("#print").on("click", function() {
    print(); 
});

$("button").on("click", function() {
	if($(this).attr('name') === 'del') {
		console.log("LA RESERVACIÓN CON EL ID: " + $(this).val() + " SERÁ BORRADA!!");
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
				callAjax("2017-09-29");
				console.log("FECHA X DIA: " + fecha_x_dia);
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


