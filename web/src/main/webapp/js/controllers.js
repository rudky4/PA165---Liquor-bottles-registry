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