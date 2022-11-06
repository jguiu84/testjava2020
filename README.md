Project answering technical test for job offers.

#### What they ask to be done

At the DB of company e-commerce we have table PRICES that shows the final price (RRP/PVP) and the rate that applies to a Product of a Brand between dates.

Example of the table with main columns (and example data):

```text
PRICES
-------

BRAND_ID         START_DATE                                    END_DATE                        PRICE_LIST                   PRODUCT_ID  PRIORITY                 PRICE           CURR
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1         2020-06-14-00.00.00                        2020-12-31-23.59.59                        1                        35455                0                        35.50            EUR
1         2020-06-14-15.00.00                        2020-06-14-18.30.00                        2                        35455                1                        25.45            EUR
1         2020-06-15-00.00.00                        2020-06-15-11.00.00                        3                        35455                1                        30.50            EUR
1         2020-06-15-16.00.00                        2020-12-31-23.59.59                        4                        35455                1                        38.95            EUR

Columns:

BRAND_ID: foreign key of the Brand of the holding (1 = ZARA).
START_DATE , END_DATE: date range when the rate price applies.
PRICE_LIST: Rate Identifier.
PRODUCT_ID: Product Identifier.
PRIORITY: Defines which rate apply if more than one date range match. Grater priority (higher number) applies in case of matching.
PRICE: final rate (RRP/PVP).
CURR: currency iso code.
```
To be done:

Build a SpringBoot application/service that provides a query endpoint that:

Accept as params:
* date
* product identifier
* brand identifier

And returns as data output:
* product identifier
* brand identifier
* rate to apply
* date range that applies
* final price

Is mandatory to use an in memory DB (like h2) and initialize with the example data (columns names can be changed and new columns can be added, choose the column type according data).

Develop tests tot the REST endpoint that validate next queries to the service with example data:
* Test 1: query for day 14 at 10:00 for the product 35455 for brand 1 (ZARA)
* Test 2: query for day 14 at 16:00 for the product 35455 for brand 1 (ZARA)
* Test 3: query for day 14 at 21:00 for the product 35455 for brand 1 (ZARA)
* Test 4: query for day 15 at 10:00 for the product 35455 for brand 1 (ZARA)
* Test 5: query for day 16 at 21:00 for the product 35455 for brand 1 (ZARA)

#### Main URLs

H2 Console<br>
```text
/h2
```

brands endpoint<br>
```text
GET /api/brands
```
rate endpoint<br>
```text
GET /api/brands/<brandId>/products/<productId>/tarifa?date=<date>
Example:
GET /api/brands/1/products/35455/tarifa?date=2020-06-14T16:00:00
Response JSON Example:
{
"idProduct": 35455,
"idBrand": 1,
"priceList": 2,
"startDate": "2020-06-14T15:00:00",
"endDate": "2020-06-14T18:30:00",
"price": 24.45
}
```

#### Used Library
* SpringBoot 2.7.5 
* SpringData with JPA
* h2 database 2.1

#### Compile
Is a Maven app.
`mvn clean package` will generate a .jar at /application/target folder

#### Run
`java -jar application-0.0.1-SNAPSHOT.jar`
<br>Requires Java 17
App runs listening at localhost:8080

##### Run as docker

(Tested with docker engine version 20.10.20)
At root folder, build the docker image:
`docker build --tag=test-java-app:latest .`
Run as docker container:
`docker run -p8888:8080 test-java-app:latest`
will run the app and mapping container port 8080 to host port 8888,
so app will be listening at localhost:8888.

Base image is `eclipse-temurin` based on alpine linux because eclipse-temurin is commonly used and alpine linux is lightweight.

#### Design decisions made

###### Java version
Used Java 17, mainly because is LTS. 

###### Multi-module
Persistence layer is on a separate module, just in case app grows and need reusing.  

###### Criteria Query
For the implementation of the relatively complex query to get the rate that applied conditioned to range date and priority ordering, Criteria Query has been chosen over other alternatives like JPQL(@Query,@NamedQuery annotation), HQL or native query.
From my perspective, Criteria Query is more flexible to build dynamic queries and safer when programming detecting errors on compiling time. 
And, of course, not depends on the actual RDBMS.

###### Centralized REST Error
Error (exception) messages are centralized with com.example.application.exception using @ExceptionHandler in conjunction with @ControllerAdvice and a custom defined class ApiError. 

###### Dates
Dates in API REST are always in date time iso format: 2020-06-14T16:00:00

#### Patterns applied

###### Repository Pattern
###### DTO Pattern

#### Clean Code principles applied

###### Naming Convention
All variables, methods and classes use CamelCase style. The only difference: a first letter of class should be capital.
Names also tend to be _use intention-revealing_ and pronounceable.

###### Single Responsability Principle

###### Small Methods

#### Pending

* Logging
* API First: OpenAPI definition (swagger file)
* Hexagonal architecture (loosely coupled)
* Testing Pyramid, missing Unit Testing and End2End Testing
* SonarQube integration with centralized rules (in case of multi projects/multi teams environment). Currently SonarLint running on IDE.
* Migrate DTO using to Record and MapStruct
* Implement CQRS Pattern 