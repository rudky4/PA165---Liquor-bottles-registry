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


liquorServices.factory('manufacturerProductionFactory', ['$http',
    function($http){
        var urlBaseToxic="http://localhost:8080/pa165/rest/manufacturer/{id}/production/toxic";
        var urlBaseNontoxic="http://localhost:8080/pa165/rest/manufacturer/{id}/production/nontoxic";

        var dataFactory={};

        dataFactory.getAllToxicProduction = function(id, success, error) {
            return $http.get(urlBaseToxic.replace("{id}", id)).then(success, error);
        };

        dataFactory.getAllNonToxicProduction = function(id, success, error) {
            return $http.get(urlBaseNontoxic.replace("{id}", id)).then(success, error);
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

liquorServices.factory('roleFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/user/role/is/police";
        var dataFactory={};

        dataFactory.isPolice = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

