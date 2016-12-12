var liquorControllers = angular.module("liquorControllers", ['liquorServices']);


liquorControllers.controller('bottleCtrl', function ($scope, $rootScope, $location, bottleFactory) {
    bottleFactory.getAllBottles(
        function (response) {
            $scope.bottles = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
});

liquorControllers.controller('manufacturerCtrl', function ($scope, $rootScope, $location, manufacturerFactory) {
    manufacturerFactory.getAllManufacturers(
        function (response) {
            $scope.manufacturers = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
});


liquorControllers.controller('manufacturerProductionCtrl', function ($scope, $rootScope, $location, $routeParams, manufacturerProductionFactory) {
    manufacturerProductionFactory.getAllToxicProduction(
        $routeParams.id,
        function (response) {
            $scope.toxicBottles = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
    manufacturerProductionFactory.getAllNonToxicProduction(
        $routeParams.id,
        function (response) {
            $scope.nonToxicBottles = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
});

liquorControllers.controller('storeCtrl', function ($scope, $rootScope, $location, storeFactory) {
    storeFactory.getAllStores(
        function (response) {
            $scope.stores = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
});