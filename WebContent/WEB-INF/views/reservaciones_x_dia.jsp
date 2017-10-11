<%-- Document: reservaciones_x_dia Created on: 9/05/2017, 11:27:16 AM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html lang="es">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1" />
    </head>
    <body>
        <section id="section_table">
            <c:if test="${lst_reservaciones.size() == 0}">
            	<div class="card_personalizada">
            		<p class="p_card_personalizada" id="p_no_reservaciones">NO ENCONTRÉ RESERVACIONES PARA EL ${diames}</p>
            	</div>            	
            </c:if>
            <c:if test="${lst_reservaciones.size() > 0}">
            	<c:forEach var="temp" items="${lst_reservaciones}">
            		<div class="card_personalizada" id="${temp.id}">
                    	<p class="p_card_personalizada">${temp.horas_entity_id_horaini.getHora()} - ${temp.horas_entity_id_horafin.getHora()}</p>
                    	<p class="p_card_personalizada">${temp.recursos_entity.getNombre()}</p>
                    	<p class="p_card_personalizada">${temp.acomodos_entity.getAcomodos()} ${temp.no_participantes}</p>
                    	<p class="p_card_personalizada">${temp.evento}</p>
                    	<p class="p_card_personalizada">${temp.requerimientos}</p>
                    	<p class="p_card_personalizada">${temp.responsable}</p>
                    	<input type="hidden" id="h${temp.id}" value="${temp.id_repetir}" />
                    	<sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_ALMACEN'})">
                    		<button id="btn_edit" value="${temp.id}" class="btns" name="edit">
								<span id="pencil" title="Modificar" class="fa icon-pencil2 icons"></span>
							</button>
							<button id="btn_del" value="${temp.id}" class="btns" name="del">
								<span id="trash" title="Cancelar" class="icon-bin2 icons"></span>
							</button>
                    	</sec:authorize>					
                	</div>
            	</c:forEach>
            </c:if>
        </section>
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/sweetalert2.min-6.6.5.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/reservaciones_x_dia.js" />' ></script>
    </body>
</html>


<!--
<p class="header">HORA: </p>
<p class="header">RECURSO: </p>
<p class="header">ACOMODO: </p>
<p class="header">EVENTO: </p>
<p class="header">REQUERIMIENTOS: </p>
<p class="header">RESPONSABLE:</p>
-->
