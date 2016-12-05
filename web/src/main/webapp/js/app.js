var module = angular.module('liquorBottleRegisitry', ['ngRoute', 'liquorControllers', 'liquorServices']);

module.config(function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html'
        })
        .when('/bottle', {
            templateUrl: 'partials/bottle.html',
            controller: 'bottleCtrl'
        })
        .otherwise({ redirectTo: '/' });
});
