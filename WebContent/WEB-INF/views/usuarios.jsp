<%-- Document: usuarios Created on: 18/05/2017, 04:10:37 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <title>Usuarios</title>
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/usuarios.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/font-awesome.min.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/ligature.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/sweetalert2.min-6.6.5.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/iconmoon-material-icons.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/iconmoon_free.css" />' />
    </head>
    <body>
        <section id="section_encabezado">
            <header>
                <div class="container">
                    <p>Lista de usuarios de la aplicación</p>
                </div>
            </header>
        </section>
        <section id="section_cuerpo">
            <div class="container">
            	<c:forEach var="temp" items="${lst_usuarios}">
            		<div class="card_personalizada">
            			<p class="p_card_personalizada">${temp.nombre_completo}</p>
                        <p class="p_card_personalizada">${temp.usuario}</p>
                        <p class="p_card_personalizada">${temp.roles_entity.getRole()}</p>
                        <span class="fa fa-pencil icons" id="pencil"></span><span id="trash" class="icon-bin2 icons"></span>
                    </div>
                </c:forEach>                        
                <p><c:out value="${resultado}" /></p>
            </div>
        </section>
        <section id="section_add_users" class="modal">
            <span id="close_modal" class="fa fa-window-close-o"></span>            
            <div class="container">
                <p>Agregar Usuario</p>
                <sf:form action="${pageContext.request.contextPath}/usuarios/save" method="POST" commandName="usuario">
                    <div class="element_form">
                        <sf:input class="lsf symbol" placeholder="user Nombre Completo" path="nombre_completo" type="text" autofocus="" />
                        <sf:input class="lsf symbol" placeholder="user Usuario" path="usuario" type="text" />
                    </div>
                    <div id="box_select" class="element_form">
                        <sf:select id="role" name="rol" path="id_rol">
                            <option value="">ROL</option>
                            <c:forEach items="${lst_roles}" var="roles">
                            	<option value="${roles.id_roles}">${roles.role}</option>
                            </c:forEach>
                        </sf:select>
                    </div>
                    <div class="element_form">
                        <input class="lsf symbol" placeholder="eye Contraseña" type="password" id="pass1" />
                        <sf:input class="lsf symbol" placeholder="eye Verifica Contraseña" path="pass" type="password" id="pass2" />
                        <button id="accept">Guardar</button>
                    </div>                    
                </sf:form>
            </div>            
        </section>
        <section id="section_footer">
            <footer>
                <div id="add_user" class="container"></div>
            </footer>
        </section>
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />'></script>
        <script type="text/javascript" src='<c:url value="/res/js/sweetalert2.min-6.6.5.js" />'></script>
        <script type="text/javascript" src='<c:url value="/res/js/usuarios.js" />'></script>        
    </body>
</html>
