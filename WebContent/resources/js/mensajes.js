//function showAlert(msj) {
function showAlert() {
		swal({
			title : 'RECURSO OCUPADO',
			text : $("#inp_msj").val(),
			type : 'error',
			confirmButtonColor : '#3085d6',
			confirmButtonText : 'Aceptar'
		}).then(function() {
			history.back();
		})
}