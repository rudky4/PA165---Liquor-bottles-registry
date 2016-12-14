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

liquorControllers.controller('laboratoryCtrl', function ($scope, $rootScope, $location, laboratoryFactory) {
    laboratoryFactory.getAllLaboratories(
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

liquorControllers.controller('toxicBottleCtrl', function ($scope, $rootScope, $location, bottleFactory) {
    bottleFactory.getAllToxicBottles(
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

liquorControllers.controller('bottlesForLabCtrl', function ($scope, $rootScope, $location, laboratoryFactory) {
    laboratoryFactory.getBottlesForLab(
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

liquorControllers.controller('bottleTypeCtrl', function ($scope, $rootScope, $location, bottleTypeFactory) {
    bottleTypeFactory.getAllBottleTypes(
        function (response) {
            $scope.bottleTypes = response.data;
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

liquorControllers.controller('manufacturerProductionCtrl', function ($scope, $rootScope, $location, $routeParams, manufacturerProductionFactory) {
    manufacturerProductionFactory.getProduction(
        $routeParams.id,
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
    manufacturerProductionFactory.getManufacturer(
        $routeParams.id,
        function (response) {
            $scope.manufacturer = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
});

liquorControllers.controller('storeBottlesCtrl', function ($scope, $rootScope, $location, $routeParams, storeBottlesFactory) {
    storeBottlesFactory.getStore(
        $routeParams.id,
        function (response) {
            $scope.store = response.data;
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
    storeBottlesFactory.getBottles(
        $routeParams.id,
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

liquorControllers.controller('manufacturerManagementCtrl', function ($scope, $rootScope, $location, $routeParams, loggedUserFactory, manufacturerProductionFactory, bottleTypeFactory) {
    loggedUserFactory.getManufacturer(
        function (response) {
            if(response.data != null) {
                $scope.loadManufacturer(response.data.id);
                $scope.loadBottleTypes(response.data.id);
            }
        },
        function(response) {
            if (response.status == 403) {
                $rootScope.page = $location.path();
                $location.path("/unauthorized");
            }
        }
    );
    $scope.loadManufacturer = function(id) {
        manufacturerProductionFactory.getManufacturer(id,
            function (response) {
                $scope.manufacturer = response.data;
            },
            function(response) {
                if (response.status == 403) {
                    $rootScope.page = $location.path();
                    $location.path("/unauthorized");
                }
            }
        );
    };
    $scope.loadBottleTypes = function(id) {
        manufacturerProductionFactory.getBottleTypes(id,
            function (response) {
                $scope.bottleTypes = response.data;
            },
            function(response) {
                if (response.status == 403) {
                    $rootScope.page = $location.path();
                    $location.path("/unauthorized");
                }
            }
        );
    };

    $scope.bottleType = {}
    $scope.create = function() {
        bottleTypeFactory.createBottleType($scope.bottleType, $scope.manufacturer.id,
            function (response) {
                $scope.bottleType = {}
                $scope.loadBottleTypes($scope.manufacturer.id);
            },
            function(response) {
                if (response.status == 403) {
                    $rootScope.page = $location.path();
                    $location.path("/unauthorized");
                }
            }
        );
    };
});
