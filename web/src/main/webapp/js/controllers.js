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
            function () {
                laboratoryFactory.getBottlesForLab(
                    function (response) {
                        $scope.bottles = response.data;
                    },
                    $rootScope.unsuccessfulResponse
                );
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
            $scope.bottlesRequestEnds = true;
            $scope.toxicBottlesPercentage = '0';
            if($scope.bottles.length != 0) {
                $scope.toxicBottlesPercentage = $rootScope.getToxicBottlesPercentage($scope.bottles);
            }
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



liquorControllers.controller('storeBottlesCtrl', function ($scope, $rootScope, $routeParams, storeFactory, bottleFactory) {
    storeFactory.getStore(
        $routeParams.id,
        function (response) {
            $scope.store = response.data;
        },
        $rootScope.unsuccessfulResponse
    );

    $scope.bottlesRequestEnds = false;
    if ($rootScope.role == 'ROLE_POLICE') {
        $scope.loadBottles = function() {
            storeFactory.getAllBottles(
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
        };
        $scope.loadBottles();

        $scope.markAsToxic = function(bottleId) {
            bottleFactory.assignToLab(bottleId,
                function (response) {
                    $scope.loadBottles();
                },
                $rootScope.unsuccessfulResponse
            );
        };
    } else {
        storeFactory.getNontoxicBottles(
            $routeParams.id,
            function (response) {
                $scope.bottlesRequestEnds = true;
                $scope.bottles = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    }
});

liquorControllers.controller('manufacturerManagementCtrl', function ($scope, $rootScope, $routeParams, loggedUserFactory, manufacturerFactory, bottleTypeFactory) {
    loggedUserFactory.getManufacturer(
        function (response) {
            if(response.data != null) {
                $scope.manufacturer = response.data;
                $scope.loadBottleTypes($scope.manufacturer.id);
            }
        },
        $rootScope.unsuccessfulResponse
    );
    $scope.loadBottleTypes = function(id) {
        manufacturerFactory.getBottleTypesAll(id,
            function (response) {
                $scope.bottleTypes = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    };

    $scope.bottleType = {}
    $scope.create = function() {
        $scope.bottleType.manufacturerId = $scope.manufacturer.id;
        bottleTypeFactory.createBottleType($scope.bottleType,
            function (response) {
                $scope.bottleType = {}
                $scope.loadBottleTypes($scope.manufacturer.id);
            },
            function (response) {
                if (response.status == 403) {
                    $rootScope.page = $location.path();
                    $location.path("/forbidden");
                } else if (response.status == 401) {
                    $window.location.href = "login.html"
                } else if (response.status == 400) {
                    document.getElementById('errorOutput').style.display = 'block';
                    setTimeout(function(){
                        document.getElementById('logoutOutput').style.display = 'none';
                    }, 3000)
                }
            }
        );
    };

    $scope.setDeleted = function(bottleType) {
        bottleType.deleted = true
        bottleTypeFactory.updateBottleType(bottleType,
            function (response) {
                $scope.loadBottleTypes($scope.manufacturer.id);
            },
            $rootScope.unsuccessfulResponse
        );
    };
    $scope.introduceType = function(bottleType) {
        bottleType.deleted = false
        bottleTypeFactory.updateBottleType(bottleType,
            function (response) {
                $scope.loadBottleTypes($scope.manufacturer.id);
            },
            $rootScope.unsuccessfulResponse
        );
    };
});

liquorControllers.controller('storeManagementCtrl', function ($scope, $rootScope, $routeParams, loggedUserFactory, storeFactory, manufacturerFactory, bottleFactory) {
    loggedUserFactory.getStore(
        function (response) {
            $scope.store = response.data;
            $scope.loadBottles($scope.store.id);
//            $scope.loadBottleTypes($scope.store.id);
        },
        $rootScope.unsuccessfulResponse
    );
//    $scope.loadBottleTypes = function(id) {
//        storeFactory.getBottleTypes(id,
//            function (response) {
//                $scope.bottleTypes = response.data;
//            },
//            $rootScope.unsuccessfulResponse
//        );
//    };
    $scope.loadBottles = function(id) {
        storeFactory.getNontoxicBottles(id,
            function (response) {
                $scope.bottles = response.data;
            },
            $rootScope.unsuccessfulResponse
        );
    };
    $scope.manufacturerSelected = function() {
        manufacturerFactory.getBottleTypes($scope.manufacturer.id,
            function (response) {
                $scope.manufacturer.bottleTypes = response.data;
                $scope.bottle.bottleType = $scope.manufacturer.bottleTypes[0];
            },
            $rootScope.unsuccessfulResponse
        );
    }

    manufacturerFactory.getAllManufacturers(
        function (response) {
            $scope.manufacturers = response.data;
        },
        $rootScope.unsuccessfulResponse
    );

    $scope.bottle = {}
    $scope.importBottle = function() {
        $scope.bottle.storeId = $scope.store.id;
        bottleFactory.createBottle($scope.bottle,
            function (response) {
                $scope.bottle = {}
                $scope.loadBottles($scope.store.id);
//                $scope.loadBottleTypes($scope.store.id);
            },
            $rootScope.unsuccessfulResponse
        );
    };

    $scope.markSold = function(bottleId) {
        bottleFactory.deleteBottle(bottleId,
            function (response) {
                $scope.loadBottles($scope.store.id);
//                $scope.loadBottleTypes($scope.manufacturer.id);
            },
            $rootScope.unsuccessfulResponse
        );
    };
});
