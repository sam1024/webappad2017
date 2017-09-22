<%-- Document: reservaciones_x_dia Created on: 9/05/2017, 11:27:16 AM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html lang="es">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/font-awesome.min.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/reservaciones_x_dia.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/iconmoon-material-icons.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/iconmoon_free.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/sweetalert.css" />' />
    </head>
    <body>
        <section id="section_table">
            <div id="fecha_reservacion">
                <input type="hidden" name="fechaxdia" id="fechaxdia" value="${fecha}" />
                <h1 id="show_fecha"></h1>
                <span id="print" class="fa fa-print"></span>
            </div>
            <c:if test="${lst_reservaciones.size() == 0}">
            	<div class="card_personalizada">
            		<p class="p_card_personalizada">No encontre nada</p>
            	</div>
            </c:if>
            <c:forEach var="temp" items="${lst_reservaciones}">
                <div class="card_personalizada">
                    <p class="p_card_personalizada">${temp.horas_entity_id_horaini.getHora()} - ${temp.horas_entity_id_horafin.getHora()}</p>
                    <p class="p_card_personalizada">${temp.recursos_entity.getNombre()}</p>
                    <p class="p_card_personalizada">${temp.acomodos_entity.getAcomodos()}</p>
                    <p class="p_card_personalizada">${temp.evento}</p>
                    <p class="p_card_personalizada">${temp.responsable}</p>
<%-- 					<input type="hidden" id="id" value="${temp.id}" name="id"> --%>
<%-- 					<input type="hidden" id="reservacion" value="${temp}" name="reservacion"> --%>
					<button id="btn_edit" value="${temp.id}" class="btns" name="edit">
						<span id="pencil" title="Editar" class="fa fa-pencil-square-o icons"></span>
					</button>
					<button id="btn_del" value="${temp.id}" class="btns" name="del">
						<span id="trash" title="Cancelar" class="icon-bin2 icons"></span>
					</button>
                </div>
            </c:forEach>
        </section>
        <section></section>
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/sweetalert2.min-6.6.5.js" />' ></script>
<%--         <script type="text/javascript" src='<c:url value="/res/js/reservaciones.js" />' ></script> --%>
        <script type="text/javascript" src='<c:url value="/res/js/reservaciones_x_dia.js" />' ></script>
        <script type='text/javascript'>showFecha();</script>
<!--         <script type='text/javascript'>fillArray(${lst_reservaciones.size()});</script> -->
    </body>
</html>
