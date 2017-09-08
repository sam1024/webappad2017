<%-- Document: reservaciones Created on: 4/05/2017, 03:55:33 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/font-awesome.min.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/reservaciones.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/iconmoon_entypo.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/iconmoon_free.css" />' />
        <title>Webappad - Reservaciones de salones</title>
    </head>
    <body>
        <section id="section_encabezado">
            <header>
            	<span id="icon_calendar" class="fa fa-calendar"></span>
                <p id="p_app_name">WebappAD</p>
                <span id="icon_menu" class="fa fa-bars"></span>                
                <nav id="nav_menu">
                	<ul id="ul_menu">
                		<li class="li_menu">
                        	<a href="<c:url value='/' />"><i class="fa icon-home icons_menu" title="Inicio"><p class="p_menu">Inicio</p></i></a>
                        </li>
                        <li class="li_menu">
                         	<a href="<c:url value='/reservacion_new' />"><i class="fa fa-pencil icons_menu" title="Nueva Reservación"><p class="p_menu">Nueva Reservación</p></i></a>
                        </li>
                        <li class="li_menu">
                        	<a href="<c:url value='#' />"><i class="fa icon-search icons_menu" title="Buscar"><p class="p_menu">Buscar</p></i></a>
                        </li>
                        <li class="li_menu" id="li_print">
                        	<a href="<c:url value='/reservaciones' />"><i class="fa fa-print icons_menu" title="Imprimir"><p class="p_menu">Imprimir</p></i></a>
                        </li>                         
                    </ul>
                </nav>
            </header>
        </section>
        <section id="section_calendar">
            <div class="container">
                <div id="navegacion_meses">
                    <a id="link_back" href="javascript:void(0)"><span id="back" class="fa fa-chevron-circle-left" /></a>
                    <div id="mes_year">
                        <p id="mes" class="p_mes_year"></p>
                        <p id="year" class="p_mes_year"></p>
                    </div>
                    <a id="link_next" href="javascript:void(0)"><span id="next" class="fa fa-chevron-circle-right" /></a>
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
        
        <!-- EN ESTE DIV SE PONDE EL ICON DE + PARA LA NUEVA RESERVACIÓN -->
<!--         <div id="new_reservacion"><span class="icon-circle-with-plus" id="icon_new_reservacion" title="Nueva"></span></div> -->
        
        <input id="path" type="hidden" value="${pageContext.request.contextPath}" />
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
        <script type="text/javascript" src='<c:url value="/res/js/reservaciones.js" />' ></script>        
        <script type='text/javascript'>showCalendar();</script>
    </body>
</html>
