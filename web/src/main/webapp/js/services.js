/**
 * Created by mhajas on 8/4/15.
 */
var liquorServices = angular.module('liquorServices', []);
var urlBase = "http://localhost:8080/pa165/rest";

liquorServices.factory('bottleFactory', ['$http',
    function($http){
        var urlBottle = urlBase.concat("/bottle");
        var urlBottleId = urlBottle.concat("/{id}");
        var urlToxic = urlBottleId.concat("/toxicity/{value}");
		var dataFactory={};
		
        dataFactory.getAllBottles = function(success, error) {
            return $http.get(urlBottle).then(success, error);
        };
		
		dataFactory.getAllToxicBottles = function(success, error) {
            return $http.get(urlBottle.concat("/toxic")).then(success, error);
        };
				
		dataFactory.setToxic = function(bottleId, toxicity, success, error) {
			return $http.put(urlToxic.replace("{id}", bottleId).replace("{value}", toxicity)).then(success, error);
		};

		dataFactory.assignToLab = function(bottleId, success, error) {
            return $http.put(urlBottleId.replace("{id}", bottleId)).then(success, error);
        };

		dataFactory.createBottle = function(bottle, storeId, success, error) {
   			return $http.post(urlBottle + "?store=" + storeId, bottle).then(success, error);
   		};

   		dataFactory.deleteBottle = function(bottleId, success, error) {
           	return $http.delete(urlBottleId.replace("{id}",bottleId)).then(success, error);
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

liquorServices.factory('bottleTypeFactory', ['$http',
    function($http){
        var urlBottleType = urlBase.concat("/bottleType");
        var urlWithId = urlBottleType.concat("/{id}");
        var dataFactory={};

        dataFactory.getAllBottleTypes = function(success, error) {
            return $http.get(urlBottleType).then(success, error);
        };

        dataFactory.createBottleType = function(bottleType, manufacturerId, success, error) {
            var finalUrl = urlWithId.replace("{id}", manufacturerId);
            return $http.post(finalUrl, bottleType).then(success, error);
        };

        dataFactory.deleteBottleType = function(id, success, error) {
            var finalUrl = urlWithId.replace("{id}", id);
            return $http.delete(finalUrl).then(success, error);
        };
        dataFactory.updateBottleType = function(bottleType, success, error) {
            return $http.put(urlBottleType, bottleType).then(success, error);
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

        dataFactory.getStore = function(success, error) {
            return $http.get(urlUser.concat("/store")).then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('manufacturerFactory', ['$http',
    function($http){
        var urlManufacturers = urlBase.concat("/manufacturer");
        var urlManufacturer = urlManufacturers.concat("/{id}");
        var dataFactory={};

        dataFactory.getAllManufacturers = function(success, error) {
            return $http.get(urlManufacturers).then(success, error);
        };

        dataFactory.getManufacturer = function(id, success, error) {
            return $http.get(urlManufacturers + "/" + id).then(success, error);
        };

        dataFactory.getProduction = function(id, success, error) {
            return $http.get(urlManufacturer.replace("{id}", id) + "/production").then(success, error);
        };

        dataFactory.getBottleTypes = function(id, success, error) {
            return $http.get(urlManufacturer.replace("{id}", id) + "/bottleTypes").then(success, error);
        };

        dataFactory.getBottleTypesAll = function(id, success, error) {
            return $http.get(urlManufacturer.replace("{id}", id) + "/bottleTypes" + "?deleted=1").then(success, error);
        };

        return dataFactory;
    }
]);

liquorServices.factory('storeFactory', ['$http',
    function($http){
        var urlStore = urlBase + "/store";
        var urlNontoxicBottles = urlStore.concat("/{id}/bottles/nontoxic");
        var urlAllBottles = urlStore.concat("/{id}//bottles/all");

        var dataFactory={};

        dataFactory.getAllStores = function(success, error) {
            return $http.get(urlStore).then(success, error);
        };

        dataFactory.getStore = function(id, success, error) {
            return $http.get(urlStore + "/" + id).then(success, error);
        };

        dataFactory.getNontoxicBottles = function(id, success, error) {
            return $http.get(urlNontoxicBottles.replace("{id}", id)).then(success, error);
        };

        dataFactory.getAllBottles = function(id, success, error) {
            return $http.get(urlAllBottles.replace("{id}", id)).then(success, error);
        };

        dataFactory.getBottleTypes = function(id, success, error) {
            return $http.get(urlStore + "/" + id + "/bottleType").then(success, error);
        };

        dataFactory.gotStoreByBottleType = function(id, success, error) {
            return $http.get(urlStore + "/bottleType/" + id).then(success, error);
        };

        return dataFactory;
    }
]);

