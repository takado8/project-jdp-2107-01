# E-commerce

## Project description

This project demonstrates REST API implementation of E-commerce store, based on DB design model.

![DB Model](https://github.com/piotr-grosicki/project-jdp-2107-01/blob/main/src/main/resources/images/models.png)

## Demo

Currently, there is no deployed version of this project, only source code is available.   

## Env Requirements

Connection to db (default mysql) set in application.properties

SpringBoot version - 2.1.18.RELEASE

```
spring.datasource.url=jdbc:mysql://localhost:3306/xxx?serverTimezone=Europe/Warsaw&useSSL=False&allowPublicKeyRetrieval=true
spring.datasource.username=xxx
spring.datasource.password=xxx 
```

## How to run

From terminal window in project root run
```
gradle build
```
Run EcommerceeAppliction (Intelij keyboard shortcut Shift + F10)

## Project endpoints

![CartController API](https://github.com/piotr-grosicki/project-jdp-2107-01/blob/main/src/main/resources/images/cart-controller.png)
![GroupController API](https://github.com/piotr-grosicki/project-jdp-2107-01/blob/main/src/main/resources/images/group-controller.png)
![OrderController API](https://github.com/piotr-grosicki/project-jdp-2107-01/blob/main/src/main/resources/images/order-controller.png)
![UserController API](https://github.com/piotr-grosicki/project-jdp-2107-01/blob/main/src/main/resources/images/user-controller.png)
![ProductController API](https://github.com/piotr-grosicki/project-jdp-2107-01/blob/main/src/main/resources/images/product-controller.png)


## Practical use case

## Troubleshooting

