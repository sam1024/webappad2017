<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!Doctype html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0"> 
		<link rel="icon" type="image/png" href='<c:url value="/res/images/kernel.png" />' />
		<title>Inventario - WebappAD</title>
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/inventory.css" />' />
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/brankic.css" />' />
	</head>
	<body>
		<%@include file="plantillas/header.jsp" %>
		<span class="icon-plus" id="add_new"></span>
		<section id="section_form">					
			<div id="modal">
					<input name="articulo" id="articulo" type="text" class="input_field" placeholder="Articulo"/>
					<select name='id_marca' id="marca" class="input_field">
						<option value="">MARCA</option>
						<c:forEach var="tmp_marcas" items="${lst_marcas}">
							<option value="${tmp_marcas.id}">${tmp_marcas.marca}</option>
						</c:forEach>
					</select>					
					<select name='id_modelo' id="modelo" class="input_field">
						<option value="">MODELO</option>
						<c:forEach var="tmp_modelos" items="${lst_modelos}">
							<option value="${tmp_modelos.id}">${tmp_modelos.modelo}</option>
						</c:forEach>
					</select>
					<input name="serie" id="serie" type="text" class="input_field" placeholder="Serie"/>
					<select name='id_recurso' id="recurso" class="input_field">
						<option value="">UBICACIÓN</option>
						<c:forEach var="tmp_recursos" items="${lst_recursos}">
							<option value="${tmp_recursos.id_recursos}">${tmp_recursos.nombre}</option>
						</c:forEach>
					</select>
					<input name="horas" id="horas" type="text" class="input_field" placeholder="Horas"/>
					<textarea name="condiciones" id="condiciones" class="input_field" placeholder="Condiciones"></textarea>
					<textarea name="comentarios" id="comentarios" class="input_field" placeholder="Comentarios"></textarea>
					<select name='id_status' id="status" class="input_field">						
						<option value="1">ACTIVO</option>
						<option value="0">BAJA</option>						
					</select>
					<input type="hidden" id="path" value="${pageContext.request.contextPath}" />
					<button class="btns" id="btn_save">Guardar</button>
					<button class="btns" id="btn_cancel">Cancelar</button>			
			</div>
		</section>
		<script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
		<script type="text/javascript" src='<c:url value="/res/js/inventory.js" />' ></script>		
	</body>
</html>

<%-- <sf:form commandName="inventario"> --%>
<%-- 					<sf:input path="articulo" name="articulo" type="text" class="input_field" placeholder="Articulo"/> --%>
<!-- 					<div class="select-wrapper"> -->
<!-- 					<select name='id_marca' class="input_field"> -->
<!-- 						<option value="">MARCA</option> -->
<%-- 						<c:forEach var="tmp_marcas" items="${lst_marcas}"> --%>
<%-- 							<option value="${tmp_marcas.id}">${tmp_marcas.marca}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<!-- 					</div> -->
<!-- 					<select name='id_modelo' class="input_field"> -->
<!-- 						<option value="">MODELO</option> -->
<%-- 						<c:forEach var="tmp_modelos" items="${lst_modelos}"> --%>
<%-- 							<option value="${tmp_modelos.id}">${tmp_modelos.modelo}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<%-- 					<sf:input path="serie" name="serie" type="text" class="input_field" placeholder="Serie"/> --%>
<!-- 					<select name='id_recurso' class="input_field"> -->
<!-- 						<option value="">UBICACIÓN</option> -->
<%-- 						<c:forEach var="tmp_recursos" items="${lst_recursos}"> --%>
<%-- 							<option value="${tmp_recursos.id_recursos}">${tmp_recursos.nombre}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 					</select> -->
<%-- 					<sf:input path="horas" name="horas" type="text" class="input_field" placeholder="Horas"/> --%>
<%-- 					<sf:textarea path="condiciones" name="condiciones" class="input_field" placeholder="Condiciones"/> --%>
<%-- 					<sf:textarea path="comentarios" name="comentarios" class="input_field" placeholder="Comentarios"/> --%>
<!-- 					<button class="btns" id="btn_save">Guardar</button> -->
<!-- 					<button class="btns" id="btn_save">Cancelar</button> -->
<%-- 				</sf:form>	 --%>
	