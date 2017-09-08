$("#print").on("click", function() {
    print(); 
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