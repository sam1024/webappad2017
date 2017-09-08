<%-- Document: roles Created on: 24/05/2017, 02:56:26 PM Author: sam --%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
        <!-- link rel="shortcut icon" type="image/x-icon" href='< c:url value="/res/images/favicon.ico" />' / -->
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/roles.css" />' />
        <link rel="stylesheet" type="text/css" href='<c:url value="/res/css/fontawesome.min.css" />' />
        <title>Roles</title>
    </head>
    <body>
        <section>
            <header id="header_roles">
                <div class="container">
                    <h2>Roles de Usuarios</h2>
                </div>
            </header>
        </section>
        <section id="section_main">
            <main id="main_roles">
                <div class="container">
                    <table>
                        <thead>
                            <tr><th>ID</th><th>ROL</th></tr>
                        </thead>
                        <tbody>
                            <c:forEach var="temp" items="${lst_roles}">
                                <tr><td>${temp.id_roles}</td><td>${temp.role}</td></tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </main>
        </section>
    </body>
</html>
