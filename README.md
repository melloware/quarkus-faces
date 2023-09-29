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

### Metrics
***
The following client and server metrics were captured while hitting the exact same page [/datatable/crud.xhtml](https://www.primefaces.org/showcase/ui/data/datatable/crud.xhtml)
Using `Incognito Mode` and pressing CTRL+F5 so it forced the browser to load all resources from the server with nothing cached.

Metric                |  WildFly EE | Quarkus (DEV)  | Quarkus (JVM)  | Quarkus (GraalVM) |
----------------------| ----------  | ---------------| ---------------|-------------------|
Package Size          | 48.5 MB WAR | N/A            | 91 MB          | 206 MB            |
Cold Startup          | 10.3 s      | 8.092 s        | 3.847 s        | 0.035 s           |
Memory Used           | 140 MB      | 113 MB         | 21 MB          | 13.6 MB           |
HTTP Requests         | 80          | 80             | 61             | 61                |
Resource Size         | 2.4 MB      | 2.4 MB         | 2.4 MB         | 2.4 MB            |
Transferred Size      | 2.4 MB      | 2.4 KB         | 888 KB         | 888 KB            |
DOM Loaded            | 1150 ms     | 1750 ms        | 918 ms         | 580 ms            |
Lighthouse Score      | 59/100      | 72/100         | 97/100         | 98/100            |
First Paint           | 2.4 s       | 2.3 s          | 0.8 s          | 0.8 s             |
Speed Index           | 2.4 s       | 2.3 s          | 1.0 s          | 0.8 s             |
Time To Interactive   | 3.9 s       | 2.3 s          | 0.9 s          | 0.8 s             |
Largest Paint         | 2.7 s       | 2.6 s          | 1.1 s          | 1.0 s             |


### Development

***
To run the example in Dev mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
mvn quarkus:dev
```

Then open your web browser to http://localhost:8081/

### Production

***
To run the example in HotSpot Production mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
mvn clean package
java -jar target/quarkus-app/quarkus-run.jar
```

Then open your web browser to http://localhost:8081/

### Docker JVM

***
Builds a Docker image running as a standard JVM application.
```
mvn clean package -Ddocker
docker run -i --rm -p 8081:8081 melloware/quarkus-faces:latest
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

