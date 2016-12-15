var liquorControllers = angular.module("liquorControllers", ['liquorServices']);


liquorControllers.controller('laboratoryCtrl', function ($scope, $rootScope, laboratoryFactory) {
    laboratoryFactory.getAllLaboratories(
        function (response) {
            $scope.laboratories = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('toxicBottleCtrl', function ($scope, $rootScope, bottleFactory) {
    bottleFactory.getAllToxicBottles(
        function (response) {
            $scope.bottles = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('bottlesForLabCtrl', function ($scope, $rootScope, laboratoryFactory, bottleFactory) {
    laboratoryFactory.getBottlesForLab(
        function (response) {
            $scope.bottles = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
    $scope.setToxic = function(bottleId, toxicity) {
        bottleFactory.setToxic(bottleId, toxicity,
            function (response) {
            },
            $rootScope.unsuccessfulResponse
        );
    };
});

liquorControllers.controller('bottleTypeCtrl', function ($scope, $rootScope, bottleTypeFactory) {
    bottleTypeFactory.getAllBottleTypes(
        function (response) {
            $scope.bottleTypes = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('manufacturerCtrl', function ($scope, $rootScope, manufacturerFactory) {
    manufacturerFactory.getAllManufacturers(
        function (response) {
            $scope.manufacturers = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('storeCtrl', function ($scope, $rootScope, storeFactory) {
    storeFactory.getAllStores(
        function (response) {
            $scope.stores = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('manufacturerProductionCtrl', function ($scope, $rootScope, $routeParams, manufacturerProductionFactory) {
   $scope.bottlesRequestEnds = false;

    manufacturerProductionFactory.getProduction(
        $routeParams.id,
        function (response) {
            $scope.bottles = response.data;
            $scope.bottlesRequestEnds = true;
            $scope.toxicBottlesPercentage = '0';
            if($scope.bottles.length != 0) {
                $scope.toxicBottlesPercentage = $rootScope.getToxicBottlesPercentage($scope.bottles);
            }
        },
        $rootScope.unsuccessfulResponse
    );
    manufacturerProductionFactory.getManufacturer(
        $routeParams.id,
        function (response) {
            $scope.manufacturer = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('storeBottlesCtrl', function ($scope, $rootScope, $routeParams, storeBottlesFactory) {
    storeBottlesFactory.getStore(
        $routeParams.id,
        function (response) {
            $scope.store = response.data;
        },
        $rootScope.unsuccessfulResponse
    );

    $scope.bottlesRequestEnds = false;

    if ($rootScope.role == 'ROLE_POLICE') {
        storeBottlesFactory.getAllBottles(
            $routeParams.id,
            function (response) {
                $scope.bottlesRequestEnds = true;
                $scope.bottles = response.data;
                $scope.toxicBottlesPercentage = '0';
                if($scope.bottles.length > 0) {
                    $scope.toxicBottlesPercentage = $rootScope.getToxicBottlesPercentage($scope.bottles);
                }
            },
            $rootScope.unsuccessfulResponse
        );
    } else {
        storeBottlesFactory.getNontoxicBottles(
            $routeParams.id,
            function (response) {
                $scope.bottlesRequestEnds = true;
                $scope.bottles = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    }
});

liquorControllers.controller('manufacturerManagementCtrl', function ($scope, $rootScope, $routeParams, loggedUserFactory, manufacturerProductionFactory, bottleTypeFactory) {
    loggedUserFactory.getManufacturer(
        function (response) {
            if(response.data != null) {
                $scope.loadManufacturer(response.data.id);
                $scope.loadBottleTypes(response.data.id);
            }
        },
        $rootScope.unsuccessfulResponse
    );
    $scope.loadManufacturer = function(id) {
        manufacturerProductionFactory.getManufacturer(id,
            function (response) {
                $scope.manufacturer = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    };
    $scope.loadBottleTypes = function(id) {
        manufacturerProductionFactory.getBottleTypes(id,
            function (response) {
                $scope.bottleTypes = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    };

    $scope.bottleType = {}
    $scope.create = function() {
        bottleTypeFactory.createBottleType($scope.bottleType, $scope.manufacturer.id,
            function (response) {
                $scope.bottleType = {}
                $scope.loadBottleTypes($scope.manufacturer.id);
            },
            $rootScope.unsuccessfulResponse
        );
    };
});
