<%-- Document: reservacion_new Created on: 2/06/2017, 02:40:43 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/font-awesome.min.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/reservacion_new.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/default.date.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/default.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/sweetalert.css" />' />
        <title>WebappAD - Nueva Reservación</title>
    </head>
    <body>
        <section id="section_header">
            <header>
                <h1>WebappAD</h1>
                <span id="icon_menu" class="fa fa-ellipsis-v"></span>
                <nav id="nav_menu">
                    <ul id="ul_menu">
                        <li class="li_menu">
                        	<a href="<c:url value='/' />"><i class="fa fa-home" title="Inicio"><p class="p_menu">Inicio</p></i></a>
                        </li>
                        <li class="li_menu">
                        	<a href="<c:url value='/reservaciones' />"><i class="fa fa-calendar" title="Reservaciones"><p class="p_menu">Reservaciones</p></i></a>
                        </li>                        
                        <li class="li_menu">
                        	<a href="<c:url value='/logout' />"><i class="fa fa-sign-out" title="Salir"><p class="p_menu">Salir</p></i></a>
                        </li>
                    </ul>
                </nav>
            </header>
        </section>
        <section id="section_main">
            <main>
                <p>Nueva reservación</p>
                <sf:form id="form_new_reservacion" action="${pageContext.request.contextPath}/reservacion/save" 
                         method="POST" commandName="reservacion_new">
                    <div class="container">
                        <sf:input path="fecha" type="text" name="fecha" id="fecha" class="input_field" placeholder="Fecha"/>
                        <select class="input_field" name="hora_inicio" id="hora_inicio">
                            <option value="">Inicia</option>
                            <c:forEach var="horas" items="${lst_horas}">
                                <option value="${horas.id_horas}">${horas.hora}</option>
                            </c:forEach>
                        </select>
                        <select class="input_field" name="hora_fin" id="hora_fin">
                            <option value="">Termina</option>
                            <c:forEach var="horas" items="${lst_horas}">
                                <option value="${horas.id_horas}">${horas.hora}</option>
                            </c:forEach>
                        </select>
                        <select class="input_field" name="id_recurso" id="recurso">
                            <option value="">Salón</option>
                            <c:forEach var="salon" items="${lst_recursos}">
                                <option value="${salon.id_recursos}">${salon.nombre}</option>
                            </c:forEach>
                        </select>
                        <select class="input_field" name="id_acomodo" id="acomodo">
                            <option value="">Acomodo</option>
                            <c:forEach var="acomodo" items="${lst_acomodos}">
                                <option value="${acomodo.id}">${acomodo.acomodos}</option>
                            </c:forEach>
                        </select>
                        <sf:textarea path="evento" name="evento" id="evento" placeholder="Evento" class="input_field textarea"></sf:textarea>
                        <sf:input path="responsable" type="text" name="responsable" id="responsable" class="input_field" placeholder="Responsable"/>
                        
                        <textarea class="input_field textarea" name="fechas" id="fechas" placeholder="Se repite"></textarea>
                        
                        <sf:input path="fecha_creacion" type="hidden" name="fecha_creacion" id="fecha_creacion" />
                        <sf:input path="id_repetir" type="hidden" name="inp_repeat" id="repeat" value="" />
                        <sf:input path="cancelada" type="hidden" name="inp_cancelada" id="cancel" value="0" />
                        <sf:input path="tipo" type="hidden" name="inp_tipo" id="tipo" value="1" />
<!--                         <input type="hidden" name="fechas" id="fechas" value="" > -->
                        
                        <sec:authorize access="isFullyAuthenticated()">
                        	<sec:authentication property="principal" var="principal" />
                            <c:set var="username" value="${principal}" />
                            <input type="hidden" name="username" id="username" value="${username}" />
                        </sec:authorize>                        
                        
                        <button type="submit" id="btn_save" class="btns">Guardar</button>
                        <button id="btn_repeat" class="btns">Repetir</button>
                        <button type="reset" id="btn_clean" class="btns">Cancelar</button>
                    </diV>
                </sf:form>
                <input id="path" type="hidden" value="${pageContext.request.contextPath}" />
            </main>
        </section>
        <section id="flotante">
        	<div id="actividades-modal" class="modal">
			<div id="navegacion_meses">
				<a id="link_back" href="javascript:void(0)"><span id="back" class="fa fa-chevron-circle-left" /></a>
				<div id="mes_year">
					<p id="mes" class="p_mes_year"></p>
					<p id="year" class="p_mes_year"></p>
				</div>
				<a id="link_next" href="javascript:void(0)"><span id="next" class="fa fa-chevron-circle-right" /></a>
			</div>
			<table>
					<thead>
						<tr>
							<th>Dom</th><th>Lun</th><th>Mar</th><th>Mié</th><th>Jue</th><th>Vie</th><th>Sáb</th>
						</tr>
					</thead>
					<tbody id="dias_mes">
					</tbody>
				</table>
				<button class="btns" id="btn_acp_repeat">Aceptar</button>
				<button class="btns" id="btn_can_repeat">Cancelar</button>
        	</div>
        </section>
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>        
        <script type="text/javascript" src='<c:url value="/res/js/picker.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/picker.date.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/legacy.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/sweetalert2.min-6.6.5.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/reservacion_new.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/calendario.js" />' ></script>
        <script type="text/javascript" >showCalendar()</script>
    </body>
</html>
