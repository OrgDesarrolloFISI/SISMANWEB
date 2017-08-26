$(document).ready(function(){
	$("#actualizadatos").click (function() {
		alertify.confirm("<h3>Esta seguro que desea editar los datos ?</h3>", function (e) {
            if (e) {
                  alertify.success("Editado con exito");
            } else { 
                        alertify.error("Cancelado");
            }
      }); 
	});
	
	 });

$('#show').click(function(e) {
	  
	  // Resetear, por si acaso has estado jugando con la otra propiedad
	  $('#element').css('visibility', 'visible');
	  
	  if( $('#element').is(":visible") ) {
	    $('#element').css('display', 'none'); 
	  } else {
	    $('#element').css('display', 'block');
	  }
	});