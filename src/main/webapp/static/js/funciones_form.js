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

$(document).ready(function(){
	  $("#hide").click(function(){
	    $("#element").hide();
	  });
	  $("#show").click(function(){
	    $("#element").show();
	  });
	});