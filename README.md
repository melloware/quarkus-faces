<div align="center">
<img src="https://github.com/melloware/quarkus-faces/blob/main/src/site/QuarkusFaces.svg" width="100%"/>
</div>
<br>

[![License](https://img.shields.io/github/license/melloware/quarkus-faces?style=for-the-badge&logo=mit)](https://opensource.org/licenses/MIT)
[![Discord Chat](https://img.shields.io/badge/discord-join_chat-blueviolet.svg?style=for-the-badge&logo=discord)](https://discord.gg/gzKFYnpmCY)
[![Java CI with Maven](https://img.shields.io/github/actions/workflow/status/melloware/quarkus-faces/maven.yml?branch=main&logo=GitHub&style=for-the-badge)](https://github.com/melloware/quarkus-faces/actions/workflows/maven.yml)
[![Quarkus](https://img.shields.io/badge/quarkus-power-blue?logo=quarkus&style=for-the-badge)](https://github.com/quarkusio/quarkus)
![GitHub Sponsors](https://img.shields.io/github/sponsors/melloware?style=for-the-badge&color=gold)

### Sponsoring

***

**If you like this project, please consider supporting me ❤️**

[![GitHub Sponsor](https://img.shields.io/badge/GitHub-FFDD00?style=for-the-badge&logo=github&logoColor=black)](https://github.com/sponsors/melloware)
[![PayPal](https://img.shields.io/badge/PayPal-00457C?style=for-the-badge&logo=paypal&logoColor=white)](https://www.paypal.me/mellowareinc)

### Goals

***
The main goal was to take an out-of-the-box Faces (formerly JSF)
application ([PrimeFaces Showcase](https://github.com/primefaces/primefaces/tree/master/primefaces-showcase))
and get it running in [Quarkus](https://quarkus.io/) and deployed as a GraalVM Native executable or an UberJar single
executable JAR.

### Application

***
See [QuarkusFaces Showcase](https://quarkus-faces-melloware-8a6a34c1.koyeb.app/) running live in GraalVM on a free cloud
hosting using 0.1 VCPU and 512MB RAM. It is an underpowered machine, but it gets the point across.

### Optimizations

***

- Apache MyFaces (Quarkus) instead of Jakarta Mojarra (Wildfly)
- PrimeFaces [MOVE_SCRIPTS_TO_BOTTOM](https://primefaces.github.io/primefaces/13_0_0/#/gettingstarted/configuration)
- Quarkus Brotli
  Compression [ quarkus.http.enable-compression=true](https://quarkus.io/guides/http-reference#http-compression)
- OmniFaces [CombinedResourceHandler](https://showcase.omnifaces.org/resourcehandlers/CombinedResourceHandler)
- PrimeFaces
  Extensions [CombinedResourceHandler Helper](https://github.com/primefaces-extensions/primefaces-extensions/issues/293)
- jQuery [Hide Page Until Complete](https://stackoverflow.com/questions/9550760/hide-page-until-everything-is-loaded-advanced/28129691#28129691)
- Caffeine [High Performance Caching](https://github.com/melloware/quarkus-faces/blob/main/src/main/java/org/primefaces/showcase/util/CaffeineCacheProvider.java)

### Development

***
To run the example in Dev mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
mvn quarkus:dev
```

Then open your web browser to http://localhost:8000/

### Production Uber-Jar

***
To run the example in HotSpot Production mode Uber-Jar:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
mvn clean package -Dquarkus.package.jar.type=uber-jar
java -jar target/quarkus-faces-runner.jar
```

Then open your web browser to http://localhost:8000/

### Docker JVM

***
Builds a Docker image running as a standard JVM application.

```
mvn clean package -Ddocker
docker run -i --rm -p 8000:8000 melloware/quarkus-faces:latest
```

### Docker Native

***
Builds a native Docker image running as a GraalVM (Mandrel) application.

```
mvn -Pnative-docker
docker run -i --rm -p 8000:8000 melloware/quarkus-faces:${version}
```

### Known Issues

***
We have a WIKI page where we are keeping track
of [known issues](https://github.com/melloware/quarkus-faces/wiki/Quarkus-Faces-Known-Issues) while developing with
Quarkus/MyFaces/PrimeFaces. Please feel free to contribute to that page if you find anything you think others should
know!

## Quarkus Insights YouTube

***
[![Quarkus Faces YouTube](http://img.youtube.com/vi/DIN0I56-GR4/0.jpg)](http://www.youtube.com/watch?v=DIN0I56-GR4 "Quarkus Insights 165 What is new with Quarkus JSF")
