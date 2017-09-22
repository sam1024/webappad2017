$("#print").on("click", function() {
    print(); 
});

$("button").on("click", function() {
	if($(this).attr('name') === 'del') {
		console.log("LA RESERVACIÓN CON EL ID: " + $(this).val() + " SERÁ BORRADA!!");
		swal({
			  title: '¿ESTAS SEGURO DE CANCELAR LA RESERVACIÓN?',
			  //text: "You won't be able to revert this!",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Aceptar',
			  cancelButtonText: 'Cancelar'
			}).then(function () {
			  swal(
			    'Deleted!',
			    'Your file has been deleted.',
			    'success'
			  )
		});
	} else {
		console.log("LA RESERVACIÓN PARA EL: " + $("#fechaxdia").val() + " CON EL ID: " + $(this).val() + " SERÁ MODIFICADA!!");
		modificar($(this).val(), $("#fechaxdia").val());		
	}
});

function showFecha() {
    array = $("#fechaxdia").val().split("-");
    fecha_split = array[0] + "/" + array[1] + "/" + array[2];    
    dia = numDayToName(new Date(fecha_split).getDay());
    $("#show_fecha").html( dia + " " + array[2] + "/" + array[1] + "/" + array[0]);
}

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

function modificar(id){ //, fecha) {
	console.log("A LA FUNCION modificar LE LLEGO EL ID: " + id + " Y LA FECHA: " + fecha);
	$.ajax({
		async: true,		
		type: "POST",
		url: $("#path").val() + "/reservacion/modificar",
		data: {"id": id,}// "fecha": fecha}
	})
	.done(function(resers) {
		success: $("#reservaciones_x_dia").html(resers);
	});
}


