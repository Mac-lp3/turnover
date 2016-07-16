'use strict';

const angular = require('angular');
const ngRoute = require('angular-route');

const turnover = angular.module('turnover', ['ngRoute']);

turnover.config(['$routeProvider', function($routeProvider) {

    // Is there a way for this to be env-dependent?
    $routeProvider.when('/', {
         templateUrl : 'app/partials/home/home.html',
         controller : 'HomeController',
         controllerAs : 'homeCtrl'
    });

}]);

turnover.controller('HomeController', ['$http', '$location', require('./partials/home/homeController')]);