/*** ABRIR LA VENTANA MODAL ***/
$("#actividades").on("click", function() {
    $("#actividades-modal").css({"top": 0, "pointer-events": "auto"});
    $(".fecha_hora").css("display", "none");
});
/*** FIN ABRIR LA VENTANA MODAL ***/

/*** CERRAR LA VENTANA MODAL ***/
$("#link_cerrar").on("click", function() {
    $("#actividades-modal").css({"top": "-117vh", "pointer-events": "none"});
    $(".fecha_hora").css("display", "block");
});
/*** FIN CERRAR LA VENTANA MODAL ***/

$("#div_icon_user").on("click", function() {
    $("#nav_menu").toggleClass("abrir_cerrar");
});

function showHoraFecha() {
    var date = new Date();
    if(date.getHours() < 10) {
        hora = "0" + date.getHours();
    } else {
        hora = date.getHours();
    }
    if(date.getMinutes() < 10) {
        minuto = "0" + date.getMinutes();
    } else {
        minuto = date.getMinutes();
    }
    
    date.getDate() < 10 ? dia = "0" + date.getDate() : dia = date.getDate();
    date.getMonth() < 10 ? mes = "0" + (date.getMonth() + 1) : mes = (date.getMonth() + 1);
    fecha_actual = dia + "/" + mes + "/" + date.getFullYear();    
    $("#fecha").html(numDiaToName(date.getDay()) + " " + dia + " " + numMesToName(date.getMonth()) + 
                     " " + date.getFullYear());
    $("#hora").html(hora + ":" + minuto);
}

function callReloj() {
    setInterval(function() {
        showHoraFecha();
    }, 1000);
    showHoraFecha();
}

/*** ESTA FUNCION CONVIERTE EL NUMERO(0 a 6) DEL DIA A NOMBRE(Dom, Lun, etc..)  ***/
function numDiaToName(numero_dia) {
    var dia = "";
    switch(numero_dia) {
        case 0:
            dia = "Dom";
            break;
        case 1:
            dia = "Lun";
            break;
        case 2:
            dia = "Mar";
            break;
        case 3:
            dia = "Mié";
            break;
        case 4:
            dia = "Jue";
            break;
        case 5:
            dia = "Vie";
            break;
        case 6:
            dia = "Sáb";
            break;
    }
    return dia;
}


/*** ESTA FUNCION CONVIERTE EL NUMERO(0 a 12) DEL MES A NOMBRE(Ene, Feb, Mar, etc...)  ***/
function numMesToName(numero_mes) {
    var mes = "";
    switch(numero_mes) {
        case 0:
            mes = "Enero";
            break;
        case 1:
            mes = "Febrero";
            break;
        case 2:
            mes = "Marzo";
            break;
        case 3:
            mes = "Abril";
            break;
        case 4:
            mes = "Mayo";
            break;
        case 5:
            mes = "Junio";
            break;
        case 6:
            mes = "Julio";
            break;
        case 7:
            mes = "Agosto";
            break;
        case 8:
            mes = "Septiembre";
            break;
        case 9:
            mes = "Octubre";
            break;
        case 10:
            mes = "Noviembre";
            break;
        case 11:
            mes = "Diciembre";
            break;
    }
    return mes;
}