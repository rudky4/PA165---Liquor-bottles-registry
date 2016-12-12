var module = angular.module('liquorBottleRegistry', ['ngRoute', 'liquorControllers', 'liquorServices']);

module.config(function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html'
        })
        .when('/bottle', {
            templateUrl: 'partials/bottle.html',
            controller: 'bottleCtrl'
        })
        .when('/manufacturer', {
            templateUrl: 'partials/manufacturers.html',
            controller: 'manufacturerCtrl'
        })
        .when('/manufacturer/:id/production', {
            templateUrl: 'partials/manufacturer_production.html',
            controller: 'manufacturerProductionCtrl'
        })
        .when('/store', {
            templateUrl: 'partials/stores.html',
            controller: 'storeCtrl'
        })
        .when('/unauthorized', {
            templateUrl: 'partials/unauthorized.html'
        })
        .otherwise({ redirectTo: '/' });
});

module.run(function($rootScope, loggedUserFactory) {
    loggedUserFactory.getPrincipal(
        function(response) {
            $rootScope.principal = response.data;
        },
        function (response) {
            alert("Error getting logged in user");
        }
    );
});

module.run(function($rootScope, roleFactory) {
    roleFactory.isPolice(
        function(response) {
            $rootScope.isPolice = response.data;
        },
        function (response) {
            alert("Error getting user role");
        }
    );
});