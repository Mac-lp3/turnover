'use strict';

const turnover = angular.module('turnover', ['ngRoute']);

turnover.config(['$routeProvider', function($routeProvider) {

    // Is there a way for this to be env-dependent?
    $routeProvider.when('/', {
         templateUrl : 'app/partials/home/home.html',
         controller : 'HomeController',
         controllerAs : 'homeCtrl'
    });

}]);