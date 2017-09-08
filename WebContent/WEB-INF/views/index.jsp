<%-- Document: index Created on: 2/05/2017, 12:41:01 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <title>Web Application Apoyo Didáctico</title>
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/index.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/font-awesome.min.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/iconmoon_entypo.css" />' />
    </head>
    <body>        
        <section id="section_actividades">
            <header id="encabezado">
                <div id="menu">
                    <a href="javascript:void(0)" id="actividades">
                        <span class="icon-grid2" title="Actividades"></span>
                        <!--Actividades-->
                    </a>
                    <div>
                        <p class="fecha_hora" id="fecha"></p>
                        <p class="fecha_hora" id="hora"></p>                        
                        <sec:authorize access="isAuthenticated()">
                            <sec:authentication property="principal" var="principal" />
                            <c:set var="username" value="${principal}" />
                            <nav id="nav_menu">
                                <ul id="datos_user">
                                    <li id="username">
                                        <span class="icon-user" id="img_user"></span>
                                        <div id="div_globo">
                                            <i></i>
                                            <p id="nombre" class="user_menu">
                                                <span class="fa icon-user user_menu"></span>                                                
                                                ${username}
                                            </p>
                                            <a href="<c:url value='/logout' />" class="user_menu">
                                                <span class="fa fa-sign-out user_menu"></span>
                                                Salir
                                            </a>
                                        </div>                                        
                                    </li>                         
                                </ul>
                            </nav>
                        </sec:authorize>                        
                    </div>
                </div>
            </header>
        </section>
        
        <!-- ***** VENTANA MODAL ****** -->
        <section id="flotante">
            <div id="actividades-modal" class="modal">
                <a id="link_cerrar" href="javascript:void(0)"><span class="fa fa-window-close-o" /></a>
                <div id="img_menus">
                    <sec:authorize access="!isAuthenticated()">
                        <!--div class="div_menu"-->
                            <a id="link_sign_in"  href="<c:url value='/login' />">
                                <span class="fa fa-sign-in sign_in">
                                    <p id="p_sign_in" class="sign_in">Iniciar sesión</p>
                                </span>
                            </a>
                        <!--/div-->
                    </sec:authorize>                    
                    <div class="div_menu">
                        <a class="link_menu" href='<c:url value="/reservaciones" />' title="Reservaciones">
                            <span class="fa fa-calendar"><p>Reservaciones</p></span>
                        </a>
                    </div>
                    <div class="div_menu">
                        <a class="link_menu" href='<c:url value="/reservacion_new" />'>
                            <span class="fa fa-pencil-square" aria-hidden="true"><p>N. Reservación</p></span>
                        </a>
                    </div>
                    <div class="div_menu">
                        <a class="link_menu" href='<c:url value="/usuarios" />'>
                            <span class="fa fa-users" aria-hidden="true"><p>Usuarios</p></span>
                        </a>
                    </div>
                    <div class="div_menu">
                        <a class="link_menu" href='<c:url value="/inventario" />'>
                            <span class="fa fa-list-alt" aria-hidden="true"><p>Inventario</p></span>
                        </a>
                    </div>
                </div>
            </div>            
        </section>
        <!-- ***** FIN VENTANA MODAL ****** -->
        
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' > </script>
        <script type="text/javascript" src='<c:url value="/res/js/index.js" />' > </script>
        <script type="text/javascript">callReloj();</script>
    </body>
</html>
