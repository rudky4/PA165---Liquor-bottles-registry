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