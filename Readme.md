# Projeto Spring Boot com GraalVM Native e OAuth2
##### _Este projeto é desenvolvido por Marcelo M Macedo e utiliza as seguintes tecnologias:_

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


### Documentação de referência
Para mais informações, por favor, considere as seguintes seções:
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

## Suporte Nativo GraalVM
Este projeto foi configurado para permitir que você gere um contêiner leve ou um executável nativo. Também é possível executar seus testes em uma imagem nativa.

### Contêiner Leve com Cloud Native Buildpacks
Se você já está familiarizado com o suporte de imagens de contêiner do Spring Boot, esta é a maneira mais fácil de começar. O Docker deve ser instalado e configurado em sua máquina antes de criar a imagem.

Para criar a imagem, execute o seguinte objetivo:

```
$ ./mvnw spring-boot:build-image -Pnative
```

 

### Executável com Ferramentas de Construção Nativa
Use esta opção se você deseja explorar mais opções, como executar seus testes em uma imagem nativa.
O compilador native-image do GraalVM deve ser instalado e configurado em sua máquina.

NOTA: É necessário o GraalVM 22.3+.

Para criar o executável, execute o seguinte objetivo:

```
$ ./mvnw clean pacckage -Pnative
```

Então, você pode executar o aplicativo da seguinte maneira:

```
$ target/areadigital-runner
```

Você também pode executar seu conjunto de testes existente em uma imagem nativa.
Esta é uma maneira eficiente de validar a compatibilidade do seu aplicativo.

Para executar seus testes existentes em uma imagem nativa, execute o seguinte objetivo:

```
$ ./mvnw test -PnativeTest
```


 
