 function parseAndSend (event) {
        // Load binary file from desktop
        loadBinaryFile(event,function(data){
            // Parse it to JSON
            var workbook = XLSX.read(data,{type:'binary'});
            // Send to server data from workbook here
            
            alert(workbook.SheetNames);
            
            var first_sheet_name = workbook.SheetNames[0];
            //var address_of_cell = 'A1';
             
            /* Get worksheet */
            var worksheet = workbook.Sheets[first_sheet_name];
             
            /* Find desired cell */
            //var desired_cell = worksheet[address_of_cell];
             
            /* Get the value */
           // var desired_value = desired_cell.v;
            
            var json_ws = XLSX.utils.sheet_to_json(worksheet);
            
            
            //alert( json_ws[0].nombre);
            
            var string_ws = JSON.stringify(json_ws);
            alert( string_ws);
            $.post("/alumno/masivo",json_ws);
            $.ajax({
                url: '/alumno/masivo',
                type: 'POST', 
                dataType: 'json',  
                data: json_ws,
                success: function(result) {
                    alert('SUCCESS');
                }
              });
            alert( json_ws[2].nombre);
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