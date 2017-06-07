<!DOCTYPE html>
<html lang="es">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Gestion Alumno</title>
	<link rel="stylesheet" href="https://bootswatch.com/sandstone/bootstrap.min.css" type="text/css"/>
</head>
<%
        if(session.getAttribute("sesion")==null){
            response.sendRedirect("login.html");
        }
        //else{
       //     response.sendRedirect("cursos.jsp");
        //}
    %> 
<body>

	
		<nav class="navbar navbar-default">
			  <div class="container-fluid">
			    <div class="navbar-header">
			      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#ejemplo1">
			        <span class="sr-only">Toggle navigation</span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			      </button>
			      <a class="navbar-brand" href="#">SISTEMA DE MANTENIMIENTO WEB</a>
			    </div>

			    <div class="collapse navbar-collapse" id="ejemplo1">
			      <ul class="nav navbar-nav">
			        <li class="active"><a href="#">Accion <span class="sr-only">(current)</span></a></li>
			        <li><a href="#">Accion</a></li>
			        <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Opciones <span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="#">Accion</a></li>
			            <li><a href="#">otra action</a></li>
			            <li><a href="#">ejemplo</a></li>
			            <li class="divider"></li>
			            <li><a href="#">Separador</a></li>
			            <li class="divider"></li>
			            <li><a href="#">un separador</a></li>
			          </ul>
			        </li>
			      </ul>
			      <form class="navbar-form navbar-left" role="search">
			        <div class="form-group">
			          <input type="text" class="form-control" placeholder="BUSCAR">
			        </div>
			        <button type="submit" class="btn btn-primary">Buscar</button>
			      </form>
			      <ul class="nav navbar-nav navbar-right">
			        <li><a href="cerrar_sesion.jsp">CERRAR SESION</a></li>
			      </ul>
			    </div>
			  </div>
		</nav>
	
	<div class="container ">
		<h3>OPCIONES</h3>
		<div class="container well ">
			<div class="row">
				<div class="col-xs-2 ">
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
									<a href="Gestion_Curso.jsp">
									<span class="glyphicon glyphicon-user" aria-expanded="true"></span>CURSOS
								</a>
								</h4>
							
						</div>

							
							<div class="panel-collapse collapse in" id="cursoBARRA">
								<div class="panel-body">
									<li>agregar</li>
									<li>ver</li>
									<li>eliminar</li>
									
								</div>
							</div>	
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
									<a href="Gestion_Alumno.jsp">
									<span class="glyphicon glyphicon-user" aria-expanded="true"></span>ALUMNO
								</a>
								</h4>
							
						</div>

							
							<div class="panel-collapse collapse" id="alumnoBARRA">
								<div class="panel-body">
									<li>agregar</li>
									<li>ver</li>
									<li>eliminar</li>
									
								</div>
							</div>	
					</div>
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
									<a href="Gestion_Docente.jsp">
									<span class="glyphicon glyphicon-user" aria-expanded="true"></span>DOCENTE
								</a>
								</h4>
							
						</div>

							
							<div class="panel-collapse collapse" id="docenteBarra">
								<div class="panel-body">
									<li>agregar</li>
									<li>ver</li>
									<li>eliminar</li>
									
								</div>
							</div>	
					</div>
					<!--<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
									<a href="Gestion_Persona.jsp">
									<span class="glyphicon glyphicon-user" aria-expanded="true"></span>Persona
								</a>
								</h4>
							
						</div>

							
							<div class="panel-collapse collapse" id="personaBarra">
								<div class="panel-body">
									<li>agregar</li>
									<li>ver</li>
									<li>eliminar</li>
									
								</div>
							</div>	
					</div>-->
						

					</div>
					
					<div class="col-xs-10 ">
					<div >
								<h2>GESTION DE CURSOS</h2>

							</div>

					<div class="panel panel-default well">
						<ul class="nav nav-tabs ">

								 <li class="active"><a href="#añadir" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-plus"></span> Añadir</a></li>
								 <li><a href="#mostrar" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-eye-open"></span> Mostrar</a></li>
								 <li><a href="#eliminar" role="tab" data-toggle="tab"><span class="glyphicon glyphicon-remove"></span> Elliminar</a></li>

							
						</ul>	
						<ul class="tab-content">
					
					  <div class="tab-pane fade in active" id="añadir">
					  	<h1>AÑADIR CURSO</h1>
					  	<br><br><br><br>
					  		<div class="form-group ">
							     <label for="cod_curso">Codigo (*)</label>
							     <input type="text " class="form-control" id="cod_curso" placeholder="Ingrese Codigo de curso" required="">
							</div>
		
					  		<div class="form-group ">
							     <label for="nombre_curso">Nombre (*)</label>
							     <input type="text " class="form-control" id="nom_curso" placeholder="Ingrese nombre de curso" required="">
							</div>
							   
							<div class="form-group">
							   <label >Plan (*)</label>
							         <select name="plan" class="form-control" >
							              <option value="1">ID PLAN  1</option>
							              <option value="2">ID PLAN  2</option>
							              <option value="3">ID PLAN  3</option>
							              <option value="4">ID PLAN  4</option>
							              <option value="5">ID PLAN  5</option>
							                      
							         </select>
							              
							 </div>
							 <div class="form-group">
							   <label >Ciclo (*)</label>
							         <select name="ciclo" class="form-control" >
							              <option value="1">Ciclo  1</option>
							              <option value="2">Ciclo  2</option>
							              <option value="3">Ciclo  3</option>
							              <option value="4">Ciclo  4</option>
							              <option value="5">Ciclo  5</option>
							              <option value="6">Ciclo  6</option>
							              <option value="7">Ciclo  7</option>
							              <option value="8">Ciclo  8</option>
							              <option value="9">Ciclo  9</option>
							              <option value="10">Ciclo 10</option>
							                      
							         </select>
							              
							 </div>
							 <div class="form-group">
							   <label >Periodo (*)</label>
							         <select name="periodo" class="form-control" >
							              <option value="1">ID Periodo 1</option>
							              <option value="2">ID Periodo 2</option>
							              <option value="3">ID Periodo 3</option>
							              <option value="4">ID Periodo 4</option>
							              <option value="5">ID Periodo 5</option>
							                      
							         </select>
							              
							 </div>
							 <div class="form-group">
			                    <label >Nro de Creditos </label>
			                    <input type="number" min="1" max="4" name="creditos" class="form-control" placeholder="Ingrese nuemro de credistos" required>
			                    <span class="help block"> Ejm : Maximo 4 creditos</span>
			                </div>
 
					  </div>
						
 
						
						<div class="tab-pane fade" id="eliminar">
 
						<h1>ELIMINAR CURSO</h1>
 
					</div>
 
							<div class="tab-pane fade" id="mostrar">
							  	<br>
							  		<ul class="list-group">
							  			<li class="list-group-item">alumno 1</li>
							  			<li class="list-group-item">alumno 2</li>
							  			<li class="list-group-item">alumno 3</li>
							  			<li class="list-group-item">alumno 4</li>
							  			<li class="list-group-item">alumno 5</li>
									</ul>
							 </div>
 				  
					</ul>
					</div>
						

					</div>
				</div>
	
			</div>
		</div>

		<div class="container">
			<div class="row">
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
				<div class="col-xs-1 well">pruebas para ver las columans </div>
			</div>
		</div>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>	
</body>
</html>