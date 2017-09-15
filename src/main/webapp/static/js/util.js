
//Funcion para activar o desactivar el Input 
 function Active() {
                if (document.getElementById("sltFiltro") != "0")
                {
                    document.getElementById("inFiltro").value = "";
                    document.getElementById("inFiltro").disabled = false;
                } else
                {
                    document.getElementById("inFiltro").value = "";
                    document.getElementById("inFiltro").disabled = true;
                }
            }