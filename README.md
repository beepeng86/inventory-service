# Inventory Service
Inventory service allows us to perform CRUD operation on inventory, implemented with spring-data-rest. 



## Guides
* after started the service, send a post call to http://localhost:8081/api/v1/inventory with request body {
  "name": "some name ",
  "count": 2
  } to populate db with inventory
* to book an inventory, post call to http://localhost:8081/api/v1/bookInventory?count=1&inventoryId=2 will book 1 item of inventory ID 2

## Improvement
* Many of the code improvement are being listed down as TODO in various part of the source code
* For db, we definitely need an actual RDBMS, and we will need to fine tune hibernate, change ddl-auto to none, explore jpa buddy for schema validation
* Include flyway/liquidbase for db upgrade
* Consider distributed caching as project grows
* Spring cloud sleuth for distributed tracing, spring cloud config for configuration
* Write relevant unit test and integration test, consider test container 
* Further, can consider service registry/discovery and spring cloud gateway for rate limiting/routing etc. Failure scenario with hytrix or other pattern


## Reference

The following guides illustrate how to use some features concretely:

* [Accessing JPA Data with REST](https://spring.io/guides/gs/accessing-data-rest/)

