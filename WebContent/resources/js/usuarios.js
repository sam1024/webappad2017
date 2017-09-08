/*** ABRIR LA VENTANA MODAL ***/
$("#add_user").on("click", function() {
    $(".modal").css({"top": "3.6rem", "pointer-events": "auto"});    
});
/*** FIN ABRIR LA VENTANA MODAL ***/

/*** CERRAR LA VENTANA MODAL ***/
$("#close_modal").on("click", function() {
    $(".modal").css({"top": "-117vh", "pointer-events": "none"});
    
});
/*** FIN CERRAR LA VENTANA MODAL ***/

/*** VERIFICAR CONTRASEÑAS IGUALES **/
$("#pass2").change(function() {
   if($("#pass1").val() !== $("#pass2").val()) {
       swal("ERROR!!!",
            "Las contraseñas no coinciden",
            "error");
       $("#pass1").val("");
       $("#pass2").val("");
       $("#pass1").focus();
   } 
});
/*** FIN VERIFICAR CONTRASEÑAS IGUALES **/


