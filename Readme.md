# Projeto Spring Boot com GraalVM Native e OAuth2
##### _Este projeto é desenvolvido por Marcelo M Macedo e utiliza as seguintes tecnologias:_
[![N|Solid](https://cldup.com/dTxpPi9lDf.thumb.png)](https://nodesource.com/products/nsolid)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)
## Tech
- Spring Boot 3.0.4
- GraalVM Native
- OAuth2
- Token
- Chave Pública e Privada

O objetivo deste projeto é demonstrar como utilizar o Spring Boot com GraalVM Native para criar uma API REST segura com OAuth2 e autenticação baseada em tokens.

### Endpoints
Os seguintes endpoints estão disponíveis na API:

- /api/v1/auth/: Endpoint principal da API.
- /api/v1/auth/login: Endpoint para autenticação do usuário e obtenção do token de acesso.
- /api/v1/auth/token: Endpoint para renovação do token de acesso.

## Instalação
Antes de começar, é necessário ter o GraalVM Native instalado em sua máquina. Para instalar o GraalVM Native, acesse este link e siga as instruções.

Em seguida, faça o clone do repositório em sua máquina:

```console
https://github.com/MarceloMacedoDev/TokenOAuth2SpringBoot3-0-4
```
Para executar o projeto, basta entrar na pasta raiz do projeto e executar o seguinte comando:

O projeto será executado na porta 8080.
 ```console
./mvnw spring-boot:run
```

### Uso
Para utilizar a API, é necessário autenticar-se utilizando o endpoint /api/v1/auth/login. Este endpoint irá retornar um token de acesso que deve ser utilizado em todas as requisições subsequentes.

O endpoint /api/v1/auth/token permite renovar o token de acesso antes que ele expire.

Contribuição
Contribuições são sempre bem-vindas! Se você deseja contribuir com este projeto, por favor, abra uma nova issue ou faça um pull request.

## Licença
Este projeto está licenciado sob a licença MIT - veja o arquivo LICENSE para mais detalhes.


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/html/#build-image)
* [GraalVM Native Image Support](https://docs.spring.io/spring-boot/docs/3.0.4/reference/html/native-image.html#native-image)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#using.devtools)
* [Spring Web](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#web)
* [Validation](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#io.validation)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#actuator)
* [Prometheus](https://docs.spring.io/spring-boot/docs/3.0.4/reference/htmlsingle/#actuator.metrics.export.prometheus)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)

### Additional Links
These additional references should also help you:

* [Configure AOT settings in Build Plugin](https://docs.spring.io/spring-boot/docs/3.0.4/maven-plugin/reference/htmlsingle/#aot)

## GraalVM Native Support

This project has been configured to let you generate either a lightweight container or a native executable.
It is also possible to run your tests in a native image.

### Lightweight Container with Cloud Native Buildpacks
If you're already familiar with Spring Boot container images support, this is the easiest way to get started.
Docker should be installed and configured on your machine prior to creating the image.

To create the image, run the following goal:

```
$ ./mvnw spring-boot:build-image -Pnative
```

Then, you can run the app like any other container:

```
$ docker run --rm -p 8080:8080 areadigital:0.0.1-SNAPSHOT
```

### Executable with Native Build Tools
Use this option if you want to explore more options such as running your tests in a native image.
The GraalVM `native-image` compiler should be installed and configured on your machine.

NOTE: GraalVM 22.3+ is required.

To create the executable, run the following goal:

```
$ ./mvnw native:compile -Pnative
```

Then, you can run the app as follows:
```
$ target/areadigital
```

You can also run your existing tests suite in a native image.
This is an efficient way to validate the compatibility of your application.

To run your existing tests in a native image, run the following goal:

```
$ ./mvnw test -PnativeTest
```


 