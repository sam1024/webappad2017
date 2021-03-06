<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"> 
		<title>WebappAD</title>
		<link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/sweetalert.css" />' />
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/mensajes.css" />' />
	</head>
	<body>
		<script type="text/javascript" src="<c:url value='/res/js/jquery-3.2.1.min.js' />" ></script>
		<script type="text/javascript" src="<c:url value='/res/js/sweetalert2.min-6.6.5.js' />" ></script>
		<script type="text/javascript" src="<c:url value='/res/js/mensajes.js' />" ></script>
		<c:if test="${sessionScope.msj != null}">
			<input type="hidden" value="${sessionScope.msj}" id="inp_msj" />
			<script type="text/javascript">main()</script>
		</c:if>
	</body>
</html>

	