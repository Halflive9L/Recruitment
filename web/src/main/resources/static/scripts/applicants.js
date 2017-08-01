/**
 * Created by Lander on 1/08/2017.
 */
var getAllApplicants = angular.module('applicant', [])
    .controller('ApplicantController', function($scope, $http) {
        $http.get('http://localhost:9090/applicant').
        then(function(response) {
            $scope.applicants = response.data;
        });
    });

