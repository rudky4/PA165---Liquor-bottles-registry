/**
 * Created by mhajas on 8/4/15.
 */
var liquorServices = angular.module('liquorServices', []);

liquorServices.factory('bottleFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/bottle";
        var urlBaseSetToxic="http://localhost:8080/pa165/rest/bottle/{id}/settoxic/{value}";
		var dataFactory={};
		
        dataFactory.getAllBottles = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };
		
		dataFactory.getAllToxicBottles = function(success, error) {
            return $http.get(urlBase.concat("/toxic")).then(success, error);
        };
				
		dataFactory.setToxic = function(bottleId, toxicity, success, error) {
			return $http.post(urlBaseSetToxic.replace("{id}", bottleId).replace("{value}", toxicity)).then(success, error);
		};

        return dataFactory;
    }
]);

liquorServices.factory('laboratoryFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/laboratory";
        var dataFactory={};

        dataFactory.getAllLaboratories = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };
		
		dataFactory.getBottlesForLab = function(success, error) {
            return $http.get(urlBase.concat("/bottles")).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('bottleTypeFactory', ['$http','$log',
    function($http,$log){
        var urlBase="http://localhost:8080/pa165/rest/bottleType";
        var urlBaseCreate="http://localhost:8080/pa165/rest/bottleType/create/{id}";
        var dataFactory={};

        dataFactory.getAllBottleTypes = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        dataFactory.createBottleType = function(bottleType, manufacturerId, success, error) {
            var finalUrl = urlBaseCreate.replace("{id}", manufacturerId);
            return $http.post(finalUrl, bottleType).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('loggedUserFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/user";
        var urlManufacturer = "http://localhost:8080/pa165/rest/user/manufacturer";
        var dataFactory={};

        dataFactory.getPrincipal = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        dataFactory.getManufacturer = function(success, error) {
            return $http.get(urlManufacturer).then(success, error);
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
        var urlBottleTypes = urlBaseManufacturer + "bottleTypes";

        var dataFactory={};

        dataFactory.getManufacturer = function(id, success, error) {
            return $http.get(urlBaseManufacturer.replace("{id}", id)).then(success, error);
        };

        dataFactory.getProduction = function(id, success, error) {
            return $http.get(urlProduction.replace("{id}", id)).then(success, error);
        };

        dataFactory.getBottleTypes = function(id, success, error) {
            return $http.get(urlBottleTypes.replace("{id}", id)).then(success, error);
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

