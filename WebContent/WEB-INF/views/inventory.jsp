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
		<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/font-awesome.min.css" />' />
	</head>
	<body>
		<section id="section_header">
			<header>
				<h1>WebappAD</h1>
				<span id="icon_menu" class="fa fa-ellipsis-v"></span>
				<nav id="nav_menu">
					<ul id="ul_menu">
						<li class="li_menu">
							<a href="<c:url value='/' />"><i class="fa fa-home" title="Inicio"><p class="p_menu">Inicio</p></i></a>
                		</li>
						<li class="li_menu">
							<a href="<c:url value='/reservaciones' />">
								<i class="fa fa-calendar" title="Reservaciones">
									<p class="p_menu">Reservaciones</p>
								</i>
							</a>
						</li>
						<li class="li_menu" id="li_new">
							<a href='javascript:void(0)' id='search'>
								<i class='fa fa-list-alt' title='Articulos'><p class='p_menu'>Artículos</p></i>
								<span class='fa fa-chevron-down'></span>
								<span class='fa fa-chevron-right'></span>
							</a> 
			        		<ul id='submenu' class="level_1">
			        			<li class="li_submenu"><a href='javascript:void(0)' id='link_add_new'><p class='p_menu'>Nuevo</p></a></li>
			        			<li id="li_search" class="li_submenu">
			        				<a href='javascript:void(0)' id='link_search'>
			        					<p class='p_menu'>Buscar</p>
			        					<span class='fa fa-chevron-down'></span>
			        					<span class='fa fa-chevron-right' id="chevron-right-anidado"></span>
			        				</a>
			        				<ul id="submenu_anidado">
			        					<li class="li_submenu_anidado"><a href='javascript:void(0)' id='search_serie'><p class='p_menu'>Por Serie</p></a></li>
			            				<li class="li_submenu_anidado"><a href='javascript:void(0)' id='search_articulo'><p class='p_menu'>Por Artículo</p></a></li>
			            				<li class="li_submenu_anidado"><a href='javascript:void(0)' id='search_area'><p class='p_menu'>Por Área</p></a></li>
			            				<li class="li_submenu_anidado"><a href='javascript:void(0)' id='search_activos'><p class='p_menu'>Activos</p></a></li>
			            				<li class="li_submenu_anidado"><a href='javascript:void(0)' id='search_bajas'><p class='p_menu'>Baja</p></a></li>
			        				</ul>
			        			</li>			        			
			        		</ul>
			    		</li>
			    		<sec:authorize access="!isAuthenticated()">
			    			<li class="li_menu">
								<a href="<c:url value='/login' />"><i class="fa fa-sign-in" title="Iniciar Sesión"><p class="p_menu">Iniciar Sesión</p></i></a>
							</li>
			    		</sec:authorize>
			    		<sec:authorize access="isAuthenticated()">
			    			<li class="li_menu">
								<a href="<c:url value='/logout' />"><i class="fa fa-sign-out" title="Salir"><p class="p_menu">Salir</p></i></a>
							</li>
			    		</sec:authorize>
					</ul>
				</nav>
			</header>
		</section>
		<sec:authorize access="isAuthenticated()">
			<span class="fa fa-plus-circle" id="add_new"></span>
		</sec:authorize>
		<section id="section_search">
			<input type="text" id="serie_find" class="input_field search_inputs hide" placeholder="Buscar por serie..." autofocus />
			<select name='id_recurso_find' id="recurso_find" class="input_field hide" onchange="showArticulos('', 0, $(this).val(), -1, $(this).attr('id'))">
				<option value="">UBICACIÓN</option>
				<c:forEach var="tmp_recursos" items="${lst_recursos}">
					<option value="${tmp_recursos.id_recursos}">${tmp_recursos.nombre}</option>
				</c:forEach>
			</select>
			<select name='id_articulo_find' id="articulo_find" class="input_field search_inputs hide" onchange="showArticulos('', $(this).val(), 0, -1, $(this).attr('id'))">
				<option value="">ARTÍCULO</option>
				<c:forEach var="tmp_articulos" items="${lst_articulos}">
					<option value="${tmp_articulos.id}">${tmp_articulos.nombre}</option>
				</c:forEach>
			</select>
		</section>
		
<!-- 		EN ESTE SECTION ES DONDE SE CARGA LA INFORMACION DE LOS ARTÍCULOS, SE HACE CON AJAX -->
		<section id="section_show_datos">
			<div class="container" id="div_articulos"></div>
		</section>
<!-- 		FIN SECTION DONDE SE CARGA LA INFORMACION DE LOS ARTÍCULOS, SE HACE CON AJAX -->

		<section id="section_form">					
			<div id="modal">
					<select name='id_articulo' id="articulo" class="input_field clean">
						<option value="">ARTÍCULO</option>
						<c:forEach var="tmp_articulos" items="${lst_articulos}">
							<option value="${tmp_articulos.id}">${tmp_articulos.nombre}</option>
						</c:forEach>
					</select>
					<select name='id_marca' id="marca" class="input_field clean">
						<option value="">MARCA</option>
						<c:forEach var="tmp_marcas" items="${lst_marcas}">
							<option value="${tmp_marcas.id}">${tmp_marcas.marca}</option>
						</c:forEach>
					</select>					
					<select name='id_modelo' id="modelo" class="input_field clean">
						<option value="">MODELO</option>
						<c:forEach var="tmp_modelos" items="${lst_modelos}">
							<option value="${tmp_modelos.id}">${tmp_modelos.modelo}</option>
						</c:forEach>
					</select>
					<input name="serie" id="serie" type="text" class="input_field clean" placeholder="Serie"/>
					<select name='id_recurso' id="recurso" class="input_field clean">
						<option value="">UBICACIÓN</option>
						<c:forEach var="tmp_recursos" items="${lst_recursos}">
							<option value="${tmp_recursos.id_recursos}">${tmp_recursos.nombre}</option>
						</c:forEach>
					</select>
					<input name="horas" id="horas" type="text" class="input_field clean" placeholder="Horas"/>
					<textarea name="condiciones" id="condiciones" class="input_field clean" placeholder="Condiciones"></textarea>
					<textarea name="comentarios" id="comentarios" class="input_field clean" placeholder="Comentarios"></textarea>
					<select name='id_status' id="status" class="input_field">						
						<option value="1">ACTIVO</option>
						<option value="0">BAJA</option>						
					</select>
					<input type="hidden" id="path" value="${pageContext.request.contextPath}" />
					<a href="javascript:void(0)" class="btns" id="btn_save">Guardar</a>
					<a href="javascript:void(0)" class="btns" id="btn_cancel">Cancelar</a>
<!-- 					<button class="btns" id="btn_save">Guardar</button> -->
<!-- 					<button class="btns" id="btn_cancel">Cancelar</button>		 -->
			</div>
		</section>
		<script type="text/javascript" src='<c:url value="/res/js/jquery-3.2.1.min.js" />' ></script>
		<script type="text/javascript" src="<c:url value='/res/js/sweetalert2.min-6.6.5.js' />" ></script>
		<script type="text/javascript" src='<c:url value="/res/js/cookie.js" />' ></script>
		<script type="text/javascript" src='<c:url value="/res/js/inventory.js" />' ></script>		
	</body>
</html>


	