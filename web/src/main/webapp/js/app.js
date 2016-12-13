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
        .when('/store/:id/bottles', {
            templateUrl: 'partials/store_bottles.html',
            controller: 'storeBottlesCtrl'
        })
        .when('/unauthorized', {
            templateUrl: 'partials/unauthorized.html'
        })
        .otherwise({ redirectTo: '/' });
});

module.run(function($rootScope) {
    $rootScope.unsuccessfulResponse = function(response) {
        if (response.status == 403) {
          $rootScope.page = $location.path();
          $location.path("/unauthorized");
        }
    }
})

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
    roleFactory.getRole(
        function(response) {
            $rootScope.role = response.data;
            $rootScope.isRole = function(roleName) {
                return $rootScope.role == roleName;
            }
        },
        function (response) {
            alert("Error getting user role");
        }
    );
});