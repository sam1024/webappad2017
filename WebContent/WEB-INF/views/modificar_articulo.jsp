<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">		
	</head>
	<body>
		<c:if test="${inventario_articulo != null}">
			<div id="div_modificar">
				<input type="hidden" id="id_articulo" name="id_articulo" value="${inventario_articulo.id}">
				<input type="hidden" id="id_marca" name="id_marca" value="${inventario_articulo.getMarcas_entity().id}">
				<input type="hidden" id="id_modelo" name="id_modelo" value="${inventario_articulo.getModelos_entity().id}">
				<input type="hidden" id="status" name="status" value="${inventario_articulo.status}">
				<input type="hidden" id="horas" name="horas" value="${inventario_articulo.horas}">
				
				<input type="text" name="articulo" class="input_field" value="${inventario_articulo.getArticulos_entity().nombre}" disabled>
				<input type="text" name="marca" class="input_field" value="${inventario_articulo.getMarcas_entity().marca}" disabled>
				<input type="text" name ="modelo" class="input_field" value="${inventario_articulo.getModelos_entity().modelo}" disabled>
				<input type="text" id="serie"class="input_field" name="serie" value="${inventario_articulo.serie}">
				<c:if test="${inventario_articulo.horas == 0}">
                	<input type="text" class="input_field" name="horas" value="${inventario_articulo.horas}" disabled>
                </c:if>
                <c:if test="${inventario_articulo.horas != 0}">
                	<input type="text" class="input_field" name="horas" value="${inventario_articulo.horas}">
                </c:if>
                <select name="ubicacion" id="ubicacion" class="input_field">
                	<option value="${inventario_articulo.getRecursos_entity_inventario().getId_recursos()}">${inventario_articulo.getRecursos_entity_inventario().getNombre()}</option>
                	<c:forEach var="tmp_recursos" items="${lst_recursos}">
                		<option value="${tmp_recursos.id_recursos}">${tmp_recursos.nombre}</option>
                	</c:forEach>
                </select>
                <textarea name="condiciones" id="condiciones" class="input_field">${inventario_articulo.condiciones}</textarea>
                <textarea name="comentarios" id="comentarios" class="input_field">${inventario_articulo.comentarios}</textarea>
                <c:if test="${inventario_articulo.status == 0}">
                	<input type="text" name="status" class="input_field" value="BAJA" disabled>
                </c:if>
                <c:if test="${inventario_articulo.status != 0}">
                	<input type="text" name="status" class="input_field" value="ACTIVO" disabled>
                </c:if>
                <button class="btns btns_upd" id="btn_save_upd">Guardar</button>
                <button class="btns btns_upd" id="btn_cancel_upd">Cancelar</button>
			</div>			
		</c:if>
		<c:if test="${inventario_articulo == null}">
			<div class="card_personalizada" id="">
				<p class="p_card_personalizada">NO ENCONTRÉ NADA</p>                						
            </div>
		</c:if>	
		<script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
		<script type="text/javascript" src='<c:url value="/res/js/modificar_articulo.js" />' ></script>			
	</body>
</html>

	