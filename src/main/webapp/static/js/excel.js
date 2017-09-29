 
var json_ws;
var string_ws;

function parseAndSend (event) {
	 
        loadBinaryFile(event,function(data){
        	
            var workbook = XLSX.read(data,{type:'binary'});
            
            var first_sheet_name = workbook.SheetNames[0];

            var worksheet = workbook.Sheets[first_sheet_name]; 
            
            json_ws = XLSX.utils.sheet_to_json(worksheet);
            string_ws = JSON.stringify(json_ws);            
            
            //alert(string_ws);
            
            //MostrarDatosAlumnos(json_ws);
        });
    }

    function loadBinaryFile(path, success) {
            var files = path.target.files;
            var reader = new FileReader();
            var name = files[0].name;
            reader.onload = function(e) {
                var data = e.target.result;
                success(data);
            };
            reader.readAsBinaryString(files[0]);
    }
    
    
    function SendDataAlumno(){
    	
    	//alert(string_ws);
    	 $.ajax({
             url: '/sismanweb/alumno/addBulk',
             type: 'POST', 
             contentType: "application/json; charset=utf-8",
             dataType: "json",  
             data: string_ws,
             success: function(data) {
            	 if(data){
                     window.location.replace("http://sitioweb.com");
                   }
             }
           });
    }
    
    
    function SendDataDocente(){
    	
    	//alert(string_ws);
    	 $.ajax({
             url: '/sismanweb/docente/addBulk',
             type: 'POST', 
             contentType: "application/json; charset=utf-8",
             dataType: "json",  
             data: string_ws,
             success: function(result) {
                 alert('SUCCESS');
             }
           });
    }
    
   /* function MostrarDatosAlumnos(data){
    	
    	if(data!=null){
    		$( "table.tabla_alumnos" ).html( "" );
    	}
    	
    	
    }*/
    
 