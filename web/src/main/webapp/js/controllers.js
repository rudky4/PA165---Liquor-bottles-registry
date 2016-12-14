var liquorControllers = angular.module("liquorControllers", ['liquorServices']);


liquorControllers.controller('laboratoryCtrl', function ($scope, $rootScope, laboratoryFactory) {
    laboratoryFactory.getAllLaboratories(
        function (response) {
            $scope.bottles = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
});

liquorControllers.controller('toxicBottleCtrl', function ($scope, $rootScope, bottleFactory) {
    bottleFactory.getAllToxicBottles(
        function (response) {
            $scope.bottles = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
});

liquorControllers.controller('bottlesForLabCtrl', function ($scope, $rootScope, laboratoryFactory) {
    laboratoryFactory.getBottlesForLab(
        function (response) {
            $scope.bottles = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
});

liquorControllers.controller('manufacturerCtrl', function ($scope, $rootScope, manufacturerFactory) {
    manufacturerFactory.getAllManufacturers(
        function (response) {
            $scope.manufacturers = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
});

liquorControllers.controller('storeCtrl', function ($scope, $rootScope, storeFactory) {
    storeFactory.getAllStores(
        function (response) {
            $scope.stores = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
});

liquorControllers.controller('manufacturerProductionCtrl', function ($scope, $rootScope, $routeParams, manufacturerProductionFactory) {
    manufacturerProductionFactory.getProduction(
        $routeParams.id,
        function (response) {
            $scope.bottles = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
    manufacturerProductionFactory.getManufacturer(
        $routeParams.id,
        function (response) {
            $scope.manufacturer = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        })
});

liquorControllers.controller('storeBottlesCtrl', function ($scope, $rootScope, $routeParams, storeBottlesFactory) {
    storeBottlesFactory.getStore(
        $routeParams.id,
        function (response) {
            $scope.store = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
    storeBottlesFactory.getBottles(
        $routeParams.id,
        function (response) {
            $scope.bottles = response.data;
        },
        function(response) {
            $rootScope.unsuccessfulResponse(response)
        }
    );
});