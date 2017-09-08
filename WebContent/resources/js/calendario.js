var date = new Date(), mes, year, number_dias_mes, name_mes, array = [], arr_fechas_rep = [];

function fillTheArray() {
    for(i = 1; i <= number_dias_mes; i++) {
        array[i] = $("#dia" + i).val();
    }
    listen();
}

function showCalendar() {
    mes = date.getMonth();
    year = date.getFullYear();
    calendar(year, mes);
}

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

$("#btn_acp_repeat").on("click", function() {
	$("#actividades-modal").css("top", "-117vh");
	if(arr_fechas_rep != null) {
		fechasRepetir(arr_fechas_rep);
	}	
	$("#fechas").css("display", "block");
});

$("#btn_can_repeat").on("click", function() {
	//arr_fechas_rep = null;
	$("#actividades-modal").css("top", "-117vh");
	$("#fechas").val("");
	//$(this).css("background-color", "#111");
});

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
                $("#semana" + numero_semana).append("<td class='diames' id='diames" + dia_mes +"' style='background: #A93226'>" +
                                                    "<input type='hidden' id='dia" + dia_mes + "' value='" + year + "-" + mesLessTen(mes + 1) +
                                      "-" + dayLessTen(dia_mes) + "' /><a class='link_day' href='javascript:void(0)' id='dia_mes" + dia_mes + "'>" +
                                       dia_mes + "</a></td>");
            } else {
                $("#semana" + numero_semana).append("<td class='diames' id='diames" + dia_mes +"'><input type='hidden' id='dia" + dia_mes + "' value='" + year + "-" + mesLessTen(mes + 1) +
                                      "-" + dayLessTen(dia_mes) + "' /><a class='link_day' href='javascript:void(0)' id='dia_mes" + dia_mes + "'>" +
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
    fillTheArray();
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

function dayLessTen(number_day) {
    number_day < 10 ? number_day = "0" + number_day : number_day = number_day;
    return number_day;
}

function mesLessTen(number_mes) {
    number_mes < 10 ? number_mes = "0" + number_mes : number_mes = number_mes;
    return number_mes;
}

function clear() {
    $("#dias_mes").empty();
}

function listen() {
    j = 1;
    array.forEach(function(fecha) {
    	$("#diames" + j).on("click", function() {
            //$(this).css("background-color", "green");
    		//if(arr_fechas_rep.length != 0) {
            if (arr_fechas_rep.includes(fecha)) {
                arr_fechas_rep.splice(arr_fechas_rep.indexOf(fecha), 1);
                $(this).css("background-color", "#111");                
              } else {
            	  if(validaDatos(fecha)) {
            		  arr_fechas_rep.push(fecha);
                      $(this).css("background-color", "green");
            	  }
                
              }
    		//}
          });
       j++;
    });
}

function validaDatos(fecha) {
	console.log("VALIDA!!");
	if(new Date($("#fecha").val()).getTime() > new Date(fecha).getTime()) {
		showAlert("La fecha no puede ser anterior a hoy");
		return false;
	} else {		
		return true;
	}
}

function fechasRepetir(arr) {
	$("#fechas").val("");
	for (var i = 0; i < arr.length; i++) {
		$("#fechas").val($("#fechas").val() + arr[i] + "\n");
	}
}

