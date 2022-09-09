[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Discord Chat](https://img.shields.io/badge/chat-discord-7289da)](https://discord.gg/gzKFYnpmCY)

Quarkus Faces
==========================

[![Quarkus Faces Logo](https://github.com/melloware/quarkus-faces/blob/main/src/site/QuarkusFaces.svg)](https://github.com/melloware/quarkus-faces)

### Goals
***
The main goal was to take an out of the box JSF application ([PrimeFaces Showcase](https://github.com/primefaces/primefaces-showcase)) 
and run it in both a Java EE Server and in [Quarkus](https://quarkus.io/). 
Some addition goals:
- See how much we can improve performance by incorporating various optimization tricks for JSF applications
- See if Quarkus is a viable option for JSF and migrating to Docker containers

### Environment
***
- OpenJDK 11.0.10
- JBoss Wildfly 18.0.1
- Quarkus 2.6.2.Final
- JSF Production Mode
- Intel(R) Core(TM) i7-8750H CPU @2.21 GHz 16GB RAM

### Optimizations
***
- Apache MyFaces (Quarkus) instead of Jakarta Mojarra (Wildfly)
- PrimeFaces [MOVE_SCRIPTS_TO_BOTTOM](https://primefaces.github.io/primefaces/10_0_0/#/gettingstarted/configuration?id=configuration)
- OmniFaces [GzipResponseFilter](https://showcase.omnifaces.org/filters/GzipResponseFilter)
- OmniFaces [CombinedResourceHandler](https://showcase.omnifaces.org/resourcehandlers/CombinedResourceHandler)
- PrimeFaces Extensions [CombinedResourceHandler Helper](https://github.com/primefaces-extensions/primefaces-extensions/issues/293) 
- jQuery [Hide Page Until Complete](https://stackoverflow.com/questions/9550760/hide-page-until-everything-is-loaded-advanced/28129691#28129691)

### Metrics
***
The following client and server metrics were captured while hitting the exact same page [/datatable/crud.xhtml](https://www.primefaces.org/showcase/ui/data/datatable/crud.xhtml)
Using `Incognito Mode` and pressing CTRL+F5 so it forced the browser to load all resources from the server with nothing cached.

Metric                |  WildFly EE | Quarkus (unoptimized) | Quarkus (optimized) | Improvement |
----------------------| ----------  | ----------------------| --------------------|-------------|
Package Size          | 48.5 MB WAR | 98 MB                 | 98 MB               | ------      |
Cold Startup          | 10.3 s      | 3.04 s                | 3.01 s              | 70.78%      |
Memory Used           | 140 MB      | 39 MB                 | 39 MB               | 72.14%      |
HTTP Requests         | 114         | 114                   | 89                  | 21.93%      |
Resource Size         | 4.4 MB      | 4.4 MB                | 4.4 MB              | -----       |
Transferred Size      | 4.4 MB      | 4.4 MB                | 2.9 MB              | 34.09%      |
DOM Loaded            | 1150 ms     | 745 ms                | 668 ms              | 41.91%      |
Lighthouse Score      | 59/100      | 61/100                | 98/100              | 66.10%      |
First Paint           | 2.4 s       | 1.0 s                 | 0.6 s               | 75.00%      |
Largest Paint         | 2.7 s       | 5.2 s                 | 1.3 s               | 51.85%      |
Speed Index           | 2.4 s       | 1.8 s                 | 0.9 s               | 62.50%      |
Time To Interactive   | 3.9 s       | 5.2 s                 | 1.3 s               | 66.67%      |


### Development

***
To run the example in Dev mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
mvn clean compile quarkus:dev
```

Then open your web browser to http://localhost:8080/

### Production

***
To run the example in HotSpot Production mode (GraalVM native-image not supported):

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces
mvn clean package
java -jar target/quarkus-app/quarkus-run.jar
```

Then open your web browser to http://localhost:8080/

### Docker JVM

***
Builds a Docker image running as a standard JVM application.
```
mvn clean package -Ddocker
docker run -i --rm -p 8080:8080 melloware/quarkus-faces:latest
```

### Docker Native

***
Builds a native Docker image running as a GraalVM (Mandrel) application. 
> **⚠️**
NOTE: not currently working because of limitations with some classloading.
```
mvn clean package -Dnative
docker build -f src/main/docker/Dockerfile.native -t melloware/quarkus-faces:${version} .
docker run -i --rm -p 8080:8080 melloware/quarkus-faces:${version}
```

### Known Issues

***
We have a WIKI page where we are keeping track of [known issues](https://github.com/melloware/quarkus-faces/wiki/Quarkus-JSF-Known-Issues) while developing with Quarkus/MyFaces/PrimeFaces. Please feel free to contribute to that page if you find anything you think others should know!

