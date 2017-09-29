var date = new Date(), mes, year, number_dias_mes, array = [], fecha_x_dia;
function sam() {
    array = [];
    for(i = 1; i <= number_dias_mes; i++) {
        array[i] = $("#dia" + i).val();
    }
    listen();
}

/*** ESTA FUNCION "CARGA" LAS RESERVACIONES AL MOMENTO DE ABRIR LA PAGINA ***/
$(function() {
    fecha = date.getFullYear() + "-" + mesLessTen((date.getMonth() + 1)) + "-" + dayLessTen(date.getDate());
    callAjax(fecha);
    showFecha(fecha);
});

$("#back").on("click", function() {
    mes -= 1;
    if (mes === -1) {
        year -= 1;
        mes = 11;
    }    
    clear();
    calendar(year, mes);
});

$("#next").on("click", function() {
    mes += 1;
    if (mes === 12) {
        year += 1;
        mes = 0;
    }
    clear();
    calendar(year, mes);
});

$("#icon_calendar").on("click", function() {
   showMenu();
});

$("#icon_menu").on("click", function() {
	showHideMenu();
});

$("#li_print").on("click", function() {
    print(); 
});

$("#new_reservacion").on("click", function() {
   $(location).attr("href", $("#path").val() + "/reservacion_new") ;
});

$("#refresh").on("click", function() {
	console.log(fecha_x_dia);
	callAjax(fecha_x_dia);
	//showHideMenu();
});

function showCalendar() {
    mes = date.getMonth();
    year = date.getFullYear();
    calendar(year, mes);
}

function calendar(year, mes) {
    $("#mes").html(numMesToName(mes));
    $("#year").html(year);
    number_dias_mes = obtNumberDayMes(mes + 1, year);
    number_dia_week_start_mes = obtNumberDayWeekStartMes(year, mes);
    day_week = 0;
    dia_mes = 1;
    dia_sig_mes = 1;
    numero_semana = 1;
    $("#dias_mes").append("<tr id='semana" + numero_semana + "'></tr>");
    while (dia_mes <= number_dias_mes) {
        if (day_week < number_dia_week_start_mes) {
            $("#semana" + numero_semana).append("<td class='diames'></td>");
            day_week++;            
        } else {
            if( (dia_mes === date.getDate()) && (mes === date.getMonth()) ) {
                $("#semana" + numero_semana).append("<td class='diames' style='background: #A93226; border-radius: .5rem'><input type='hidden' id='dia" + dia_mes + "' value='" + dayLessTen(dia_mes) + "/" + mesLessTen(mes + 1) +
                                      "/" + year + "' /><a class='link_day' href='javascript:void(0)' id='dia_mes" + dia_mes + "'>" + 
                                       dia_mes + "</a></td>");
            } else {
                $("#semana" + numero_semana).append("<td class='diames'><input type='hidden' id='dia" + dia_mes + "' value='" + dayLessTen(dia_mes) + "/" + mesLessTen(mes + 1) +
                                      "/" + year + "' /><a class='link_day' href='javascript:void(0)' id='dia_mes" + dia_mes + "'>" + 
                                      dia_mes + "</a></td>");
            }
            if(day_week === 6) {
                dia_mes++;
                day_week = 0;
                number_dia_week_start_mes = -1;
                numero_semana++;
                $("#dias_mes").append("<tr id='semana" + numero_semana + "'></tr>");
            } else {
                day_week++;
                dia_mes++;
            }
        }
    }
    sam();
}

/** OBTENER EL NUMERO DE DIAS QUE TRAE UN MES **/
function obtNumberDayMes(number_mes, year) {
    return new Date(year, number_mes, 0).getDate();
}
/** FIN OBTENER EL NUMERO DE DIAS QUE TRAE UN MES **/

/** OBTENER EL NUMERO DEL DIA DE LA SEMANA DE INICIO DEL MES **/
function obtNumberDayWeekStartMes(year, number_mes) {
  return new Date(year, number_mes, 1).getDay();
}
/** FIN OBTENER EL NUMERO DEL DIA DE LA SEMANA DE INICIO DEL MES **/

function numMesToName(number_mes) {
    switch(number_mes) {
        case 0:
            name_mes = "ENERO";
            break;
        case 1:
            name_mes = "FEBRERO";
            break;
        case 2:
            name_mes = "MARZO";
            break;
        case 3:
            name_mes = "ABRIL";
            break;
        case 4:
            name_mes = "MAYO";
            break;
        case 5:
            name_mes = "JUNIO";
            break;
        case 6:
            name_mes = "JULIO";
            break;
        case 7:
            name_mes = "AGOSTO";
            break;
        case 8:
            name_mes = "SEPTIEMBRE";
            break;
        case 9:
            name_mes = "OCTUBRE";
            break;
        case 10:
            name_mes = "NOVIEMBRE";
            break;
        case 11:
            name_mes = "DICIEMBRE";
            break;
    }
    return name_mes;
}

function clear() {
    $("#dias_mes").empty();
}

function listen() {
    j = 1;
    array.forEach(function(fecha) {
    	$("#dia_mes" + j).on("click", function() {
          showMenu();
          array_fecha = fecha.split("/");
      	  fecha_formateada = array_fecha[2] + "-" + array_fecha[1] + "-" + array_fecha[0];
          callAjax(fecha_formateada);
       });
       j++;
    });  
}

function dayLessTen(number_day) {
    number_day < 10 ? number_day = "0" + number_day : number_day = number_day;
    return number_day;
}

function mesLessTen(number_mes) {
    number_mes < 10 ? number_mes = "0" + number_mes : number_mes = number_mes;
    return number_mes;
}

function showMenu() {
    $("#section_calendar").toggleClass("abrir");
}

function showHideMenu() {
	$("#nav_menu").toggleClass("abrir_cerrar");
}

function callAjax(fecha) {
	fecha_x_dia = fecha;
	showFecha(fecha);
    $.ajax({
    	async: true,
        type: "POST",
        url: $("#path").val() + "/xdia",
        data: {"fecha": fecha},
        success: function(resers) {
        	$("#reservaciones_x_dia").html(resers);
        }
    });
}

/**** RESERVACIONES X DIA ****/
function showFecha(fecha) {
	array_xdia = fecha.split("-");
    $("#show_fecha").html(numDayToName(new Date(array_xdia[0] + "/" + array_xdia[1] + "/" + array_xdia[2]).getDay()) + 
    		" " + array_xdia[2] + "/" + array_xdia[1] + "/" + array_xdia[0]);
}


