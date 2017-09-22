<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"> 
		<title>Pagina JSP</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/inventory.css" />' />
	</head>
	<body>
		<%@include file="plantillas/header.jsp" %>
		<h1>PAGINA DE INVENTARIO</h1>
		<script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
		<script type="text/javascript" src='<c:url value="/res/js/inventory.js" />' ></script>	
	</body>
</html>

	