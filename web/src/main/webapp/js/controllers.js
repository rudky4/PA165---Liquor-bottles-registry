var liquorControllers = angular.module("liquorControllers", ['liquorServices']);


liquorControllers.controller('bottleCtrl', function ($scope, bottleFactory) {
    bottleFactory.getAllBottles(
        function (response) {
            $scope.bottles = response.data
        },
        function() {
            alert("Error getting bottles");
        }
    );
});
