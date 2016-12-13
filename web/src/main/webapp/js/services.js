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

liquorServices.factory('manufacturerFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/manufacturer";
        var dataFactory={};

        dataFactory.getAllManufacturers = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('storeFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/store";
        var dataFactory={};

        dataFactory.getAllStores = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('manufacturerProductionFactory', ['$http',
    function($http){
        var urlBaseManufacturer="http://localhost:8080/pa165/rest/manufacturer/{id}/";
        var urlProduction= urlBaseManufacturer + "production/";

        var dataFactory={};

        dataFactory.getManufacturer = function(id, success, error) {
            return $http.get(urlBaseManufacturer.replace("{id}", id)).then(success, error);
        };

        dataFactory.getProduction = function(id, success, error) {
            return $http.get(urlProduction.replace("{id}", id)).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('storeBottlesFactory', ['$http',
    function($http){
        var urlBaseStore= "http://localhost:8080/pa165/rest/store/{id}/";
        var urlBottles = urlBaseStore + "bottles/";

        var dataFactory={};

        dataFactory.getStore = function(id, success, error) {
            return $http.get(urlBaseStore.replace("{id}", id)).then(success, error);
        };

        dataFactory.getBottles = function(id, success, error) {
            return $http.get(urlBottles.replace("{id}", id)).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('roleFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/user/role/";
        var dataFactory={};

        dataFactory.getRole = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

