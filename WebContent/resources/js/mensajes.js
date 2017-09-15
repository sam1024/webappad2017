

function main() {
	var clave = $("#inp_msj").val().split("$"), title;
	console.log("CLAVE: " + clave + "\nclave[0]: " + clave[0]);
	switch(clave[0]) {
		case "1":
			console.log("CASE 1");
			title = "ERROR";
			showAlert(title, clave[1]);
			break;
		case "2":
			console.log("CASE 2");
			title = "RECURSO OCUPADO";
			showAlert(title, clave[1]);
			break;		
	}
}

function showAlert(title, msj) {
	console.log("TITULO: " + title + "\nMENSAJE: " + msj)
	swal({
		title : title,
		text : msj,
		type : 'error',
		confirmButtonColor : '#3085d6',
		confirmButtonText : 'Aceptar'
	}).then(function() {
		history.back();
	})
}



