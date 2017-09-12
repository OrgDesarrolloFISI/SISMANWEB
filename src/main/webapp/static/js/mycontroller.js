//mycontroller.js
myApp.controller('MyController',function($scope, $http) {

$scope.inicial = [];


$scope.getDataFromServer = function() {
    
        if(document.getElementById("inFiltro").value.length!=0){
            
        $http.get('/sismanweb/alumno/search?slt=' + $scope.select + '&value=' + $scope.value)
        .success(function(data, status, headers, config) {                 
                        $scope.alumnos = data;       
        }).error(function(data, status, headers, config){});
        }else{
            $scope.alumnos = $scope.inicial; 
        }
};

 $scope.Active= function() {
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
});