<%-- Document: login Created on: 6/06/2017, 02:29:55 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/font-awesome.min.css" />' />
        <link type="text/css" rel="stylesheet" href='<c:url value="/res/css/login.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/ligature.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/iconmoon_free.css" />' />
        <title>WebappAD - Inicio de sesión</title>
    </head>
    <body>
        <section>
            <header>
                <h1>WebappAD</h1>
                <nav id="nav_menu">
                    <ul id="ul_menu">
                        <li><a href="<c:url value="/" />">
                                <span class="icon-home inicio"><p class="inicio">Inicio</p></span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </header>
        </section>
        <section>
            <main>
                <c:url var="login_url" value="/login" />
                <c:if test="${param.error != null}">
                    <c:out value="${param.error}" />
                    <span style="color:red">Error de credenciales</span>
                </c:if>
                <form name="login" method="post" action="${pageContext.request.contextPath}/login">
                    <div class="container">
                        <input class="lsf symbol" type="text" name="username" id="user" autofocus="" placeholder="user Usuario" />
                        <input class="lsf symbol" type="password" name="password" id="pass" placeholder="eye Contraseña" />
                        <button id="send" name="send" type="submit">Iniciar sesión</button>
                    </div>
                </form>                
            </main>
        </section>
        <script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
    </body>
</html>
