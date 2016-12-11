/**
 * Created by mhajas on 8/4/15.
 */
var liquorServices = angular.module('liquorServices', []);

liquorServices.factory('bottleFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/bottle";
        var dataFactory={};

        dataFactory.getAllBottles = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('loggedUserFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/user";
        var dataFactory={};

        dataFactory.getPrincipal = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

