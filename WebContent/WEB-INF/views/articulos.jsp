<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!Doctype html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">		
	</head>
	<body>
	<c:if test="${flag == 0}">
		<c:if test="${lst_resultado.size() == 0}">
			<div class="card_personalizada" id="">
				<p class="p_card_personalizada">NO ENCONTRÉ NADA</p>                						
            </div>
		</c:if>
		<c:if test="${lst_resultado.size() > 0}">
			<c:forEach var="tmp_lst_resultado" items="${lst_resultado}">
				<div class="card_personalizada" id="${tmp_lst_resultado.id}">
					<p class="p_card_personalizada" id="p_articulo"><p class="p_titulo">ARTÍCULO:</p> ${tmp_lst_resultado.getArticulos_entity().nombre}</p>
                	<p class="p_card_personalizada"><p class="p_titulo">MARCA:</p> ${tmp_lst_resultado.getMarcas_entity().marca}</p>
                	<p class="p_card_personalizada"><p class="p_titulo">MODELO:</p> ${tmp_lst_resultado.getModelos_entity().modelo}</p>
                	<p class="p_card_personalizada"><p class="p_titulo">SERIE:</p> ${tmp_lst_resultado.serie}</p>
                	<c:if test="${tmp_lst_resultado.horas == 0}">
                		<p class="p_card_personalizada"><p class="p_titulo">HORAS:</p> N/A</p>
                	</c:if>
                	<c:if test="${tmp_lst_resultado.horas != 0}">
                		<p class="p_card_personalizada"><p class="p_titulo">HORAS:</p> ${tmp_lst_resultado.horas}</p>
                	</c:if>
                	<p class="p_card_personalizada"><p class="p_titulo">UBICACIÓN:</p> ${tmp_lst_resultado.getRecursos_entity_inventario().nombre}</p>
                	<p class="p_card_personalizada"><p class="p_titulo">CONDICIONES:</p> ${tmp_lst_resultado.condiciones}</p>
                	<p class="p_card_personalizada"><p class="p_titulo">COMENTARIS:</p> ${tmp_lst_resultado.comentarios}</p>
					<c:if test="${tmp_lst_resultado.status == 0}">
                		<p class="p_card_personalizada"><p class="p_titulo">ESTADO:</p> BAJA</p>
                	</c:if>
                	<c:if test="${tmp_lst_resultado.status != 0}">
                		<p class="p_card_personalizada"><p class="p_titulo">ESTADO:</p> ACTIVO</p>
                	</c:if>
                	<sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_ALMACEN'})">
                		<button id="btn_edit" value="${tmp_lst_resultado.id}" class="btns btns_art" name="edit">
							<span id="pencil" title="Modificar" class="fa fa-pencil-square-o icons"></span>
						</button>
						<button id="btn_del" value="${tmp_lst_resultado.id}" class="btns btns_art" name="del">
	 						<span id="trash" title="Cancelar" class="fa fa-trash icons"></span>
						</button>
       	        	</sec:authorize>					
            	</div>
			</c:forEach>
		</c:if>
	</c:if>
	
<!--********* 	RESULTADO DE METODO BUSCAR POR SERIE **************-->
	<c:if test="${flag == 1}">
		<c:if test="${articulo != null}">
			<div class="card_personalizada" id="${articulo.id}">
				<p class="p_card_personalizada"><p class="p_titulo">ARTÍCULO:</p> ${articulo.getArticulos_entity().nombre}</p>
				<p class="p_card_personalizada"><p class="p_titulo">MARCA:</p> ${articulo.getMarcas_entity().marca}</p>
				<p class="p_card_personalizada"><p class="p_titulo">MODELO:</p> ${articulo.getModelos_entity().modelo}</p>
				<p class="p_card_personalizada"><p class="p_titulo">SERIE:</p> ${articulo.serie}</p>
				<c:if test="${articulo.horas == 0}">
                	<p class="p_card_personalizada"><p class="p_titulo">HORAS:</p> N/A</p>
                </c:if>
                <c:if test="${articulo.horas != 0}">
                	<p class="p_card_personalizada"><p class="p_titulo">HORAS:</p> ${tmp_inventario.horas}</p>
                </c:if>
				<p class="p_card_personalizada"><p class="p_titulo">UBICACIÓN:</p> ${articulo.getRecursos_entity_inventario().nombre}</p>
				<p class="p_card_personalizada"><p class="p_titulo">CONDICIONES:</p> ${articulo.condiciones}</p>
				<p class="p_card_personalizada"><p class="p_titulo">COMENTARIS:</p> ${articulo.comentarios}</p>
				<c:if test="${articulo.status == 0}">
                	<p class="p_card_personalizada"><p class="p_titulo">ESTADO:</p> BAJA</p>
                </c:if>
                <c:if test="${articulo.status != 0}">
                	<p class="p_card_personalizada"><p class="p_titulo">ESTADO:</p> ACTIVO</p>
                </c:if>
				<sec:authorize access="hasAnyRole({'ROLE_ADMIN', 'ROLE_ALMACEN'})">
					<button id="btn_edit" value="${articulo.id}" class="btns btns_art" name="edit">
						<span id="pencil" title="Modificar" class="fa fa-pencil-square-o icons"></span>
					</button>
					<button id="btn_del" value="${articulo.id}" class="btns btns_art" name="del">
						<span id="trash" title="Cancelar" class="fa fa-trash icons"></span>
					</button>
				</sec:authorize>
			</div>
		</c:if>
		<c:if test="${articulo == null}">
			<div class="card_personalizada" id="">
				<p class="p_card_personalizada">NO ENCONTRÉ NADA</p>                						
            </div>
		</c:if>
	</c:if>
<!--********* 	FIN RESULTADO DE METODO BUSCAR POR SERIE **************-->

	<script type="text/javascript" src='<c:url value="/res/js/inventory.js" />' ></script>	
	</body>
</html>

	