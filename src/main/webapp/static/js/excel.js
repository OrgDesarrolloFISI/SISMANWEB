 
function parseAndSend (event) {
	 
        loadBinaryFile(event,function(data){
        	
            var workbook = XLSX.read(data,{type:'binary'});
            
            var first_sheet_name = workbook.SheetNames[0];

            var worksheet = workbook.Sheets[first_sheet_name];
         
            var json_ws = XLSX.utils.sheet_to_json(worksheet);
            var string_ws = JSON.stringify(json_ws);
           
            $.ajax({
                url: '/sismanweb/alumno/masivo',
                type: 'POST', 
                contentType: "application/json; charset=utf-8",
                dataType: "json",  
                data: string_ws,
                success: function(result) {
                    alert('SUCCESS');
                }
              });

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
    
 