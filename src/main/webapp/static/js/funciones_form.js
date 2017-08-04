function actualizar_datos(){
	/*id_usuario = $('#id_usuario').val();
	nombres = $('#nombresu').val();
	apellidos = $('#apellidosu').val();
	dni = $('#dniu').val();
	direccion = $('#direccionu').val();
	email = $('#emailu').val();
	telefono = $('#telefonou').val();
	password = $('#passwordu').val();

	cadena = "id_usuario="+id_usuario+ "&nombres="+nombres+"&apellidos="+apellidos+"&dni="+dni+"&direccion="+direccion+
			"&email="+email+"&telefono="+telefono+"&password="+password;*/

	/*$.ajax({
		type: "POST",
		url: "php/actualizarDatos.php",
		data: cadena,
		success:function (r) {
			if (r==1) {
				$('#tabla').load('mostrar_usuarios.php');
				alertify.success("Actualizado con éxito");
			}else{
				alertify.error("Fallo en el servidor");
			}
		}
	});
}