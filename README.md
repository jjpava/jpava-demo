# jpava-demo

[![Maintainability](https://api.codeclimate.com/v1/badges/e2087405eb3994b076d6/maintainability)](https://codeclimate.com/github/scfj/jpava-demo/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/e2087405eb3994b076d6/test_coverage)](https://codeclimate.com/github/scfj/jpava-demo/test_coverage)

Example of jpava usage. See jpava applied to typical web application.

## Run tests
From command line:
```shell script
./mvnw test
```
See tests in [TextSpecificationTest.java](src/test/java/io/github/jjpava/jpavademo/TextSpecificationTest.java).

## Run app
Run app and test manually from command line:
```shell script
./mvnw spring-boot:run
```

Create some posts:
```shell script
curl -X POST localhost:8080/posts -H 'Content-Type: application/json' \
    -d '{"title":"Hello world!","content":"In this article I will tell you how to..."}'
curl -X POST localhost:8080/posts -H 'Content-Type: application/json' \
    -d '{"title":"How to perform a search!","content":"In Spring Application..."}'
```

Find them:
```shell script
curl localhost:8080/posts?query=how
```

Project classes are [here](src/main/java/io/github/jjpava/jpavademo).

Project configuration - [application.properties](src/main/resources/application.properties).
