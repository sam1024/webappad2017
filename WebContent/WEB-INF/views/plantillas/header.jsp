<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/header.css" />' />
<link type="text/css" rel="stylesheet" href='<c:url value="/res/css/font-awesome.min.css" />' />
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
					<a href="<c:url value='/reservaciones' />"><i class="fa fa-calendar" title="Reservaciones"><p class="p_menu">Reservaciones</p></i></a>
				</li>
				<li class="li_menu" id="li_new">
				<a href='javascript:void(0)' id='search'><i class='fa fa-search' title='Buscar'><p class='p_menu'>Buscar</p></i><span class='fa fa-chevron-down'></span></a> 
			            <ul id='submenu'>
			            <li><a href='javascript:void(0)' id='search_serie'><p class='p_menu'>Por Serie</p></a></li>
			            <li><a href='javascript:void(0)' id='search_articulo'><p class='p_menu'>Por Art�culo</p></a></li>
			            <li><a href='javascript:void(0)' id='search_area'><p class='p_menu'>Por �rea</p></a></li>
			            </ul></li>
				<li class="li_menu">
					<a href="<c:url value='/logout' />"><i class="fa fa-sign-out" title="Salir"><p class="p_menu">Salir</p></i></a>
				</li>
			</ul>
		</nav>
	</header>
</section>