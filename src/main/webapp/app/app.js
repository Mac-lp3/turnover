'use strict';

const angular = require('angular');
const ngRoute = require('angular-route');
const ngMessages = require('angular-messages');
const turnover = angular.module('turnover', ['ngRoute', 'ngMessages']);

turnover.config(['$routeProvider', function($routeProvider) {

    // Is there a way for this to be env-dependent?
    $routeProvider.when('/', {
         templateUrl : 'app/partials/home/home.html',
         controller : 'HomeController',
         controllerAs : 'homeCtrl'
    });

    $routeProvider.when('/results', {
         templateUrl : 'app/partials/results/results.html',
         controller : 'ResultsController',
         controllerAs : 'resultsCtrl'
    });

}]);

turnover.service('ResultsService', ['$location', require('./js/services/resultsService')]);
turnover.controller('HomeController', ['$http', '$location', 'ResultsService', require('./partials/home/homeController')]);
turnover.controller('ResultsController', ['$http', '$location', 'ResultsService', require('./partials/results/resultsController')]);