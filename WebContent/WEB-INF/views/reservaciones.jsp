<%-- Document: reservaciones Created on: 4/05/2017, 03:55:33 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, user-scalable=no" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/reservaciones.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/iconmoon_free.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/sweetalert.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/default.date.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/default.css" />' />
        <title>Webappad - Reservaciones de salones</title>
    </head>
    <body>
        <section id="section_encabezado">
            <header>
            	<span id="icon_calendar" class="fa icon-calendar"></span>
                <p id="p_app_name">WebappAD</p>
                <span id="icon_menu" class="fa icon-menu"></span> 
                <div id="fecha_reservacion">
    	            <h1 id="show_fecha"></h1>        	        
            	</div>               
                <nav id="nav_menu">
                	<ul id="ul_menu">
                		<sec:authorize access="!isAuthenticated()">
                			<li class="li_menu">
                        		<a href="<c:url value='/login' />"><i class="icon-enter icons_menu" title="Iniciar sesión"><p class="p_menu">Inicio</p></i></a>
                        	</li>
                		</sec:authorize>                		
                		<li class="li_menu">
							<a href="<c:url value='/' />"><i class="icon-home icons_menu" title="Inicio"><p class="p_menu">Inicio</p></i></a>
                        </li>
                        <li class="li_menu">
							<a href="javascript:void(0)" id="refresh"><i class="icon-spinner11 icons_menu" title="Volver a carga"><p class="p_menu">Volver a Cargar</p></i></a>
                        </li>
                        <sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_ALMACEN'})">
                        	<li class="li_menu">
								<a href="<c:url value='/reservacion_new' />"><i class="icon-pencil icons_menu" title="Nueva Reservación"><p class="p_menu">Nueva Reservación</p></i></a>
                        	</li>
                        </sec:authorize>
                        <li class="li_menu">
							<a href="<c:url value='#' />"><i class="icon-search icons_menu" title="Buscar"><p class="p_menu">Buscar</p></i></a>
                        </li>
                        <li class="li_menu" id="li_print">
							<a href="<c:url value='/reservaciones' />"><i class="icon-printer icons_menu" title="Imprimir"><p class="p_menu">Imprimir</p></i></a>			
                        </li>
                        <sec:authorize access="isAuthenticated()">
                			<li class="li_menu">
                        		<a href="<c:url value='/logout' />"><i id="close_sesion" class="icon-exit icons_menu" title="Cerrar sesión"><p class="p_menu">Cerrar Sesión</p></i></a>
                        	</li>
                		</sec:authorize>              
                    </ul>
                </nav>
            </header>
        </section>
        <section id="section_calendar">
            <div class="container">
                <div id="navegacion_meses">
						<a id="link_back" href="javascript:void(0)"><span id="back" class="icon-circle-left next_back" /></a>
                    <div id="mes_year">
                        <p id="mes" class="p_mes_year"></p>
                        <p id="year" class="p_mes_year"></p>
                    </div>
                    <a id="link_next" href="javascript:void(0)"><span id="next" class="icon-circle-right next_back" /></a>
                </div>               
                <div id="calendar">
                    <table id="table_calendar">
                        <thead>
                            <tr>
                                <th>Dom</th><th>Lun</th><th>Mar</th><th>Mié</th><th>Jue</th><th>Vie</th><th>Sáb</th>
                            </tr>
                        </thead>
                        <tbody id="dias_mes">                            
                        </tbody>
                    </table>
                </div>
            </div>
        </section>
        <section id="section_reservaciones_x_dia">
            <div class="container" id="reservaciones_x_dia"></div>
        </section>	
        <input id="path" type="hidden" value="${pageContext.request.contextPath}" />
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/picker.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/picker.date.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/legacy.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/reservaciones.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/reservaciones_x_dia.js" />' ></script>
        <script type='text/javascript'>showCalendar()</script>
    </body>
</html>
