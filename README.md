<div align="center">
<img src="https://github.com/melloware/quarkus-faces/blob/main/src/site/QuarkusFaces.svg" width="100%"/>
</div>
<br>

[![License](https://img.shields.io/github/license/melloware/quarkus-faces?style=for-the-badge&logo=mit)](https://opensource.org/licenses/MIT)
[![Discord Chat](https://img.shields.io/badge/discord-join_chat-blueviolet.svg?style=for-the-badge&logo=discord)](https://discord.gg/gzKFYnpmCY)
[![Java CI with Maven](https://img.shields.io/github/actions/workflow/status/melloware/quarkus-faces/maven.yml?branch=main&logo=GitHub&style=for-the-badge)](https://github.com/melloware/quarkus-faces/actions/workflows/maven.yml)
[![Quarkus](https://img.shields.io/badge/quarkus-power-blue?logo=quarkus&style=for-the-badge)](https://github.com/quarkusio/quarkus)

### Goals
***
The main goal was to take an out of the box Faces (formerly JSF) application ([PrimeFaces Showcase](https://github.com/primefaces/primefaces-showcase)) 
and run it in both a Java EE Server and in [Quarkus](https://quarkus.io/). 
Some addition goals:
- See how much we can improve performance by incorporating various optimization tricks for JSF applications
- See if Quarkus is a viable option for Faces and migrating to Docker containers

### OIDC - KEYCLOAK
***
This code base modifies the demo to use Keycloak to protect the web pages.

The main dashboard page is not protected, but all of the other links in the menu are.
Clicking on any of them will redirect you to KeyCloaks login screen.

You can log in with `bob:bob` or `alice:alice` and you will see in the console output that you were logged in.

You will also see a `Logout` link appear at the top right of the screen.

Clicking it will automatically log you out and redirect you back to the main page.

You can see the various settings for oidc in the `application.properties` file.

You can learn more about OIDC at - OIDC [OIDC](https://quarkus.io/guides/security-oidc-code-flow-authentication-tutorial)


### Environment
***
- OpenJDK 17.0.5
- JBoss Wildfly 18.0.1
- Quarkus 2.16.3.Final
- JSF Production Mode
- Intel(R) Core(TM) i7-8750H CPU @2.21 GHz 16GB RAM

### Optimizations
***
- Apache MyFaces (Quarkus) instead of Jakarta Mojarra (Wildfly)
- PrimeFaces [MOVE_SCRIPTS_TO_BOTTOM](https://primefaces.github.io/primefaces/10_0_0/#/gettingstarted/configuration?id=configuration)
- Quarkus Compression [ quarkus.http.enable-compression=true](https://quarkus.io/guides/http-reference#http-compression)
- OmniFaces [CombinedResourceHandler](https://showcase.omnifaces.org/resourcehandlers/CombinedResourceHandler)
- PrimeFaces Extensions [CombinedResourceHandler Helper](https://github.com/primefaces-extensions/primefaces-extensions/issues/293) 
- jQuery [Hide Page Until Complete](https://stackoverflow.com/questions/9550760/hide-page-until-everything-is-loaded-advanced/28129691#28129691)



### Development

***
To run the example in Dev mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
git checkout oidc-auth
mvn quarkus:dev
```

Then open your web browser to http://localhost:8081/

### Production

***
To run the example in HotSpot Production mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
git checkout oidc-auth
mvn clean package
java -jar target/quarkus-app/quarkus-run.jar
```

Then open your web browser to http://localhost:8080/

### Docker JVM

***
Builds a Docker image running as a standard JVM application.
```
mvn clean package -Ddocker
docker run -i --rm -p 8081:8081 melloware/quarkus-faces:oidc-auth
```

### Docker Native

***
Builds a native Docker image running as a GraalVM (Mandrel) application. 
```
mvn -Pnative
docker run -i --rm -p 8081:8081 melloware/quarkus-faces:${version}
```

### Known Issues

***
We have a WIKI page where we are keeping track of [known issues](https://github.com/melloware/quarkus-faces/wiki/Quarkus-JSF-Known-Issues) while developing with Quarkus/MyFaces/PrimeFaces. Please feel free to contribute to that page if you find anything you think others should know!

