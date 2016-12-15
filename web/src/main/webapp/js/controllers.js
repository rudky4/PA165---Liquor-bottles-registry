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

liquorControllers.controller('bottleTypesCtrl', function ($scope, $rootScope, bottleTypeFactory) {
    $scope.header = "List of bottle types";
    bottleTypeFactory.getAllBottleTypes(
        function (response) {
            $scope.bottleTypes = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('storeBottleTypeCtrl', function ($scope, $rootScope, $routeParams, storeFactory) {
    storeFactory.gotStoreByBottleType(
        $routeParams.id,
        function (response) {
            $scope.stores = response.data;
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

liquorControllers.controller('manufacturerProductionCtrl', function ($scope, $rootScope, $routeParams, manufacturerFactory) {
    manufacturerFactory.getProduction(
        $routeParams.id,
        function (response) {
            $scope.bottles = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
    manufacturerFactory.getManufacturer(
        $routeParams.id,
        function (response) {
            $scope.manufacturer = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('manufacturerTypesCtrl', function ($scope, $rootScope, $routeParams, manufacturerFactory) {

    manufacturerFactory.getManufacturer(
        $routeParams.id,
        function (response) {
            $scope.manufacturer = response.data;
            $scope.header = "Bottles for " + $scope.manufacturer.name;
        },
        $rootScope.unsuccessfulResponse
    );

    manufacturerFactory.getBottleTypes(
        $routeParams.id,
        function (response) {
            $scope.bottleTypes = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});



liquorControllers.controller('storeBottlesCtrl', function ($scope, $rootScope, $routeParams, storeFactory) {
    storeFactory.getStore(
        $routeParams.id,
        function (response) {
            $scope.store = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
    storeFactory.getBottles(
        $routeParams.id,
        function (response) {
            $scope.bottles = response.data;
        },
        $rootScope.unsuccessfulResponse
    );
});

liquorControllers.controller('manufacturerManagementCtrl', function ($scope, $rootScope, $routeParams, loggedUserFactory, manufacturerFactory, bottleTypeFactory) {
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
        manufacturerFactory.getManufacturer(id,
            function (response) {
                $scope.manufacturer = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    };
    $scope.loadBottleTypes = function(id) {
        manufacturerFactory.getBottleTypes(id,
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
