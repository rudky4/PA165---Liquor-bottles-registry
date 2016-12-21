var module = angular.module('liquorBottleRegistry', ['ngRoute', 'liquorControllers', 'liquorServices']);

module.config(function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html'
        })
        .when('/bottleTypes', {
            templateUrl: 'partials/bottle_type.html',
            controller: 'bottleTypesCtrl'
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
        .when('/manufacturer/:id', {
            templateUrl: 'partials/bottle_type.html',
            controller: 'manufacturerTypesCtrl'
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
        .when('/store/bottleType/:id', {
            templateUrl: 'partials/stores.html',
            controller: 'storeBottleTypeCtrl'
        })
        .when('/managementManufacturer', {
            templateUrl: 'partials/manufacturer.html',
            controller: 'manufacturerManagementCtrl'
        })
        .when('/managementStore', {
            templateUrl: 'partials/store.html',
            controller: 'storeManagementCtrl'
        })
        .when('/forbidden', {
            templateUrl: 'partials/forbidden.html'
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
            $location.path("/forbidden");
        } else if (response.status == 401) {
            $window.location.href = "login.html"
        } else if (response.status == 400 || response.status == 409) {
            document.getElementById('errorOutput').style.display = 'block';
            setTimeout(function(){
                document.getElementById('errorOutput').style.display = 'none';
            }, 3000)
        }
    };

    $rootScope.getToxicBottlesPercentage = function(bottles) {
        var isToxic = function(bottle) { return bottle.toxic; }
        var toxicBottlesLength = bottles.filter(isToxic).length;
        var allBottlesLength = bottles.length;
        var percentage = (toxicBottlesLength / allBottlesLength) * 100;
        return Number((percentage).toFixed(2));
    };
});
