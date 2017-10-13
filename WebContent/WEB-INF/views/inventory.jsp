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
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/sweetalert.css" />' />
	</head>
	<body>
		<%@include file="plantillas/header.jsp" %>
		<span class="fa fa-plus-circle" id="add_new"></span>
		<section id="section_search">
			<input type="text" id="txt_search" class="input_field search_inputs" placeholder="Buscar por serie..." autofocus />
			<select name='id_recurso_find' id="recurso_find" class="input_field search_inputs">
				<option value="">UBICACIÓN</option>
				<c:forEach var="tmp_recursos" items="${lst_recursos}">
					<option value="${tmp_recursos.id_recursos}">${tmp_recursos.nombre}</option>
				</c:forEach>
			</select>
			<select name='id_articulo_find' id="articulo_find" class="input_field search_inputs">
				<option value="">ARTÍCULO</option>
				<c:forEach var="tmp_articulos" items="${lst_articulos}">
					<option value="${tmp_articulos.id}">${tmp_articulos.nombre}</option>
				</c:forEach>
			</select>
			<button id="btn_show_all" class="btns">TODOS LOS PRODUCTOS</button>
		</section>
		<section id="section_show_datos">
			<div class="container">
<%-- 				<c:forEach var="tmp_inventario" items="${lst_inventario}"> --%>
<%-- 					<div class="card_personalizada" id="${tmp_inventario.id}"> --%>
<%-- 						<p class="p_card_personalizada">${tmp_inventario.articulo}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.getMarcas_entity().marca}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.getModelos_entity().modelo}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.serie}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.horas}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.getRecursos_entity_inventario().nombre}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.condiciones}</p> --%>
<%--                     	<p class="p_card_personalizada">${tmp_inventario.comentarios}</p> --%>
<%--                     	<input type="hidden" id="h${tmp_inventario.id}" value="${}" /> --%>
<%--                    	<sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_ALMACEN'})"> --%>
<%--                     		<button id="btn_edit" value="${tmp_inventario.id}" class="btns" name="edit"> --%>
<!-- 								<span id="pencil" title="Modificar" class="fa icon-pencil2 icons"></span> -->
	<!-- 						</button> -->
	<%-- 						<button id="btn_del" value="${tmp_inventario.id}" class="btns" name="del"> --%>
<!--	 							<span id="trash" title="Cancelar" class="icon-bin2 icons"></span> -->
<!-- 							</button> -->
<%--        	             </sec:authorize>					 --%>
<!--                 	</div> -->
<%-- 				</c:forEach>				 --%>
			</div>
		</section>
		<section id="section_form">					
			<div id="modal">
					<select name='id_articulo' id="articulo" class="input_field">
						<option value="">ARTÍCULO</option>
						<c:forEach var="tmp_articulos" items="${lst_articulos}">
							<option value="${tmp_articulos.id}">${tmp_articulos.nombre}</option>
						</c:forEach>
					</select>
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
		<script type="text/javascript" src="<c:url value='/res/js/sweetalert2.min-6.6.5.js' />" ></script>
		<script type="text/javascript" src='<c:url value="/res/js/inventory.js" />' ></script>
	</body>
</html>


	