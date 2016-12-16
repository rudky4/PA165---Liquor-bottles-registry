Liquor Bottle Registy - REST

Api root URL: /pa165/rest

Roles: Manufacturer, Police, Laboratory, Store

Following does not require to be logged in:

Get all Stores: curl -X GET http://localhost:8080/pa165/rest/store

Get all Manufacturers: curl -X GET http://localhost:8080/pa165/rest/manufacturer

Get all Bottles in Store: curl -X GET http://localhost:8080/pa165/rest/store/{id}/bottles

Get all Store where is BottleType available: curl -X GET http://localhost:8080/pa165/rest/store/bottleType/{id}

Get all Bottle types: curl -X GET http://localhost:8080/pa165/rest/bottleTypes

Response example: 
[{"id":9,"name":"Absolut Vodka","volume":40.00,"size":1.00,"type":"VODKA","manufacturedBy":{"id":3,"name":"Absolut"},"deleted":false},
{"id":10,"name":"Absolut Strong","volume":65.00,"size":1.00,"type":"VODKA","manufacturedBy":{"id":3,"name":"Absolut"},"deleted":false}]

Create and delete require to be logged in:

Create new Bottle type: curl -X POST -i -H "Content-Type: application/json" --data ' http://localhost:8080/pa165/rest/bottleType/1
example: {name: "Dzama Rhum", size: "700", volume: "40", type: "RUM"} 

Delete Bottle type: curl -X GET http://localhost:8080/pa165/rest/manufacturer/1/bottleTypes?deleted=1

