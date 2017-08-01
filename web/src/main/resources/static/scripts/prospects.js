/**
 * Created by Lander on 31/07/2017.
 */
var getAllProspects = angular.module('prospect', [])
    .controller('ProspectController', function($scope, $http) {
        $http.get('http://localhost:9090/prospect').
        then(function(response) {
            $scope.prospects = response.data;
        });
    });


