
//myapp.js
var myApp = angular.module('myApp',  ['ui.bootstrap','ui.utils']);

//mycontroller.js
myApp.controller('MyController',function($scope, $http) {

	var mc = this;
	
	
$scope.inicial = [];


mc.getDataFromServer = function() {
    
        if(document.getElementById("inFiltro").value.length!=0){
            
        $http.get('/sismanweb/alumno/search?slt=' + $scope.select + '&value=' + $scope.value)
        .success(function(data, status, headers, config) {
        	alert("Recibio datos")
                        mc.lista = data;       
        }).error(function(data, status, headers, config){});
        
        }else{
        				mc.lista = $scope.inicial; 
        }
};

 mc.Active= function() {
                if ($scope.select != "0")
                {
                    document.getElementById("inFiltro").value = "";
                    document.getElementById("inFiltro").disabled = false;
                } else
                {
                    document.getElementById("inFiltro").value = "";
                    document.getElementById("inFiltro").disabled = true;
                }
            }
 
 $scope.dataTableOpt = {
		   //custom datatable options 
		  // or load data through ajax call also
		  "aLengthMenu": [[10, 50, 100,-1], [10, 50,100,'All']],
		  };
});