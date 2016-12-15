/**
 * Created by mhajas on 8/4/15.
 */
var liquorServices = angular.module('liquorServices', []);
var urlBase = "http://localhost:8080/pa165/rest";

liquorServices.factory('bottleFactory', ['$http',
    function($http){
        var urlBase="http://localhost:8080/pa165/rest/bottle";
        var urlBaseSetToxic="http://localhost:8080/pa165/rest/bottle/{id}/toxicity/{value}";
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
        var urlLab = urlBase.concat("/laboratory");
        var dataFactory={};

        dataFactory.getAllLaboratories = function(success, error) {
            return $http.get(urlLab).then(success, error);
        };
		
		dataFactory.getBottlesForLab = function(success, error) {
            return $http.get(urlLab.concat("/bottles")).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('bottleTypeFactory', ['$http','$log',
    function($http,$log){
        var urlBottleType = urlBase.concat("/bottleType");
        var urlCreate = urlBottleType.concat("/create/{id}");
        var dataFactory={};

        dataFactory.getAllBottleTypes = function(success, error) {
            return $http.get(urlBottleType).then(success, error);
        };

        dataFactory.createBottleType = function(bottleType, manufacturerId, success, error) {
            var finalUrl = urlCreate.replace("{id}", manufacturerId);
            return $http.post(finalUrl, bottleType).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('loggedUserFactory', ['$http',
    function($http){
        var urlUser = urlBase.concat("/user");
        var dataFactory={};

        dataFactory.getPrincipal = function(success, error) {
            return $http.get(urlUser).then(success, error);
        };

        dataFactory.getManufacturer = function(success, error) {
            return $http.get(urlUser.concat("/manufacturer")).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('manufacturerFactory', ['$http',
    function($http){
        var urlManufacturer = urlBase.concat("/manufacturer");
        var dataFactory={};

        dataFactory.getAllManufacturers = function(success, error) {
            return $http.get(urlManufacturer).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('storeFactory', ['$http',
    function($http){
        var urlStore = urlBase.concat("/store");
        var dataFactory={};

        dataFactory.getAllStores = function(success, error) {
            return $http.get(urlStore).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('manufacturerProductionFactory', ['$http',
    function($http){
        var urlManufacturer = urlBase.concat("/manufacturer/{id}");
        var urlProduction= urlManufacturer.concat("/production");
        var urlBottleTypes = urlManufacturer.concat("/bottleTypes");

        var dataFactory={};

        dataFactory.getManufacturer = function(id, success, error) {
            return $http.get(urlManufacturer.replace("{id}", id)).then(success, error);
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
        var urlStore = urlBase.concat("/store/{id}");
        var urlNontoxicBottles = urlStore.concat("/bottles/nontoxic");
        var urlAllBottles = urlStore.concat("/bottles/all");

        var dataFactory={};

        dataFactory.getStore = function(id, success, error) {
            return $http.get(urlStore.replace("{id}", id)).then(success, error);
        };

        dataFactory.getNontoxicBottles = function(id, success, error) {
            return $http.get(urlNontoxicBottles.replace("{id}", id)).then(success, error);
        };

        dataFactory.getAllBottles = function(id, success, error) {
            return $http.get(urlAllBottles.replace("{id}", id)).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('roleFactory', ['$http',
    function($http){
        var urlUserRole = baseUrl.concat("/user/role");
        var dataFactory={};

        dataFactory.getRole = function(success, error) {
            return $http.get(urlBase).then(success, error);
        };

        return dataFactory;
    }
]);

