var module = angular.module('liquorBottleRegistry', ['ngRoute', 'liquorControllers', 'liquorServices']);

module.config(function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html'
        })
		.when('/laboratory', {
            templateUrl: 'partials/laboratory.html',
            controller: 'laboratoryCtrl'
        })
		.when('/laboratory/bottles', {
            templateUrl: 'partials/toxic_bottle.html',
            controller: 'bottlesForLabCtrl'
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
            controller: 'storeCtrl',
            component: 'store'
        })
        .when('/store/:id/bottles', {
            templateUrl: 'partials/store_bottles.html',
            controller: 'storeBottlesCtrl'
        })
        .when('/management', {
            templateUrl: 'partials/manufacturer.html',
            controller: 'manufacturerManagementCtrl'
        })
        .when('/unauthorized', {
            templateUrl: 'partials/unauthorized.html'
        })
		.when('/bottle/toxic', {
            templateUrl: 'partials/toxic_bottle.html',
            controller: 'toxicBottleCtrl'
		})
        .otherwise({ redirectTo: '/' });
});

module.run(function($rootScope, $location, $window, loggedUserFactory) {
    loggedUserFactory.getPrincipal(
        function(response) {

            var values = JSON.parse(response.data);

            $rootScope.principal = values.username;
            $rootScope.role = values.role
        },
        function (response) {
            alert("Error getting logged in user");
        }
    );

    $rootScope.unsuccessfulResponse = function(response) {
        if (response.status == 403) {
            $rootScope.page = $location.path();
            $location.path("/unauthorized");
        } else if (response.status == 401) {
            $window.location.href = "login.html"
        }
    }
});
