<!Doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 		<meta name="viewport" content="width=device-width, user-scalable=no">
	</head>
	<body>
		<section id="section_main">
			<main>
                <p>Modificar reservación</p>
                <sf:form id="form_new_reservacion" action="${pageContext.request.contextPath}/reservacion/save" 
                         method="POST" commandName="reservacion_new">
                    <sf:input path="id" type="hidden" name="id" value="${reservacion.getId()}" />
                    <div class="container">
                        <sf:input path="fecha" value="${reservacion.getFecha()}" type="text" name="fecha" id="fecha" class="input_field inputs_small" placeholder="Fecha"/>
                        <select class="input_field inputs_small horas" name="hora_inicio" id="hora_inicio">
                            <option value="${reservacion.getHoras_entity_id_horaini().getId_horas()}">${reservacion.getHoras_entity_id_horaini().getHora()}</option>
                            <c:forEach var="horas" items="${lst_horas}">
                                <option value="${horas.id_horas}">${horas.hora}</option>
                            </c:forEach>
                        </select>
                        <select class="input_field inputs_small horas" name="hora_fin" id="hora_fin">
                            <option value="${reservacion.getHoras_entity_id_horafin().getId_horas()}">${reservacion.getHoras_entity_id_horafin().getHora()}</option>
                            <c:forEach var="horas" items="${lst_horas}">
                                <option value="${horas.id_horas}">${horas.hora}</option>
                            </c:forEach>
                        </select>
                        <select class="input_field" name="id_recurso" id="recurso">
                            <option value="${reservacion.getRecursos_entity().getId_recursos()}">${reservacion.getRecursos_entity().getNombre()}</option>
                            <c:forEach var="salon" items="${lst_recursos}">
                                <option value="${salon.id_recursos}">${salon.nombre}</option>
                            </c:forEach>
                        </select>
<!--                         <select class="input_field" name="id_acomodo" id="acomodo"> -->
<%--                             <option value="${reservacion.getAcomodos_entity().getId()}">${reservacion.getAcomodos_entity().getAcomodos()}</option> --%>
<%--                             <c:forEach var="acomodo" items="${lst_acomodos}"> --%>
<%--                                 <option value="${acomodo.id}">${acomodo.acomodos}</option> --%>
<%--                             </c:forEach> --%>
<!--                         </select> -->
<%--                         <input class="input_field" type="text" name="no_participantes" id="no_participantes" placeholder="No. Participantes" value="${reservacion.getNo_participantes()}" /> --%>
<%--                         <sf:textarea path="evento" value="" name="evento" id="evento" placeholder="Evento" class="input_field textarea">${reservacion.getEvento()}</sf:textarea> --%>
						<textarea name="evento" id="evento" placeholder="Evento" class="input_field textarea">${reservacion.getEvento()}</textarea>
<%--                         <sf:textarea path="requerimientos" name="requerimientos" id="requerimientos" placeholder="Requerimientos (Mesa para coffee, microfono, podium, galletas, etc.)" class="input_field textarea"></sf:textarea> --%>
<%-- 						<textarea path="requerimientos" name="requerimientos" id="requerimientos" placeholder="Requerimientos (Mesa para coffee, microfono, podium, galletas, etc.)" class="input_field textarea">${reservacion.getRequerimientos()}</textarea> --%>
                        <sf:input path="responsable" value="${reservacion.getResponsable()}" type="text" name="responsable" id="responsable" class="input_field" placeholder="Responsable"/>
                        
                        <textarea class="input_field textarea" name="fechas" id="fechas" placeholder="Se repite"></textarea>
                        
                        <sf:input path="fecha_creacion" type="hidden" name="fecha_creacion" id="fecha_creacion" />
                        <sf:input path="id_repetir" type="hidden" name="inp_repeat" id="repeat" value="${reservacion.getId_repetir()}" />
                        <sf:input path="cancelada" type="hidden" name="inp_cancelada" id="cancel" value="0" />
                        <sf:input path="tipo" type="hidden" name="inp_tipo" id="tipo" value="1" />
                        
                        <sec:authorize access="isFullyAuthenticated()">
                        	<sec:authentication property="principal" var="principal" />
                            <c:set var="username" value="${principal}" />
                            <input type="hidden" name="username" id="username" value="${username}" />
                        </sec:authorize>                                           
                        
                        <button type="submit" id="btn_save" class="btns_upd">Guardar</button>
                        <button type="reset" id="btn_cancel" class="btns_upd">Cancelar</button>
                    </diV>
                </sf:form>
<%--                 <c:if test="${reservacion.getId_repetir() != 0}"> --%>
<%--                 	<c:set var="fechas_repe" value="" /> --%>
<%--                 	<c:forEach var="tmp" items="${lst_repetidas}"> --%>
<%-- 						<c:set var="fechas_repe" value="${fechas_repe + tmp.getFecha()}" /> --%>
<%--                 	</c:forEach> --%>
<%--                 </c:if> --%>
<!--                 <input id="fechas_se_repite" type="hidden" value="fechas_repe" /> -->
                <input id="path" type="hidden" value="${pageContext.request.contextPath}" />
            </main>
        </section>
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
		<script type="text/javascript" src='<c:url value="/res/js/picker.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/picker.date.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/sweetalert2.min-6.6.5.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/reservacion_new.js" />' ></script>
<%--         <script type="text/javascript" src='<c:url value="/res/js/modificar.js" />' ></script> --%>
	</body>
</html>

	