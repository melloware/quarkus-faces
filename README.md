[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

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
- Quarkus 1.11.3
- JSF Production Mode
- Intel(R) Core(TM) i7-8750H CPU @2.21 GHz 16GB RAM

### Optimizations
***
- Apache MyFaces (Quarkus) instead of Jakarta Mojarra (Wildfly)
- PrimeFaces [MOVE_SCRIPTS_TO_BOTTOM](https://primefaces.github.io/primefaces/10_0_0/#/gettingstarted/configuration?id=configuration)
- OmniFaces [GzipResponseFilter](https://showcase.omnifaces.org/filters/GzipResponseFilter)
- OmniFaces [CombinedResourceHandler](https://showcase.omnifaces.org/resourcehandlers/CombinedResourceHandler)
- PrimeFaces Extensions [CombinedResourceHandler Helper](https://github.com/primefaces-extensions/primefaces-extensions/issues/293) 
- PrimeFaces Extensions [Optimizer](https://github.com/primefaces-extensions/resources-optimizer-maven-plugin)
- jQuery [Hide Page Until Complete](https://stackoverflow.com/questions/9550760/hide-page-until-everything-is-loaded-advanced/28129691#28129691)

### Metrics
***
The following client and server metrics were captured while hitting the exact same page [/datatable/crud.xhtml](https://www.primefaces.org/showcase/ui/data/datatable/crud.xhtml)
Using `Incognito Mode` and pressing CTRL+F5 so it forced the browser to load all resources from the server with nothing cached.

Metric                | JBoss WildFly EE | Quarkus       | Improvement    |
----------------------| ---------------  | ------------- |----------------|
Package Size          | 48.5 MB          | 7.9 MB        | 83.7%          |
Cold Startup          | 10.3 s           | 3.62 s        | 64.9%          |
Memory Used           | 140 MB           | 39 MB         | 77.8%          |
HTTP Requests         | 114              | 89            | 21.93%         |
Resource Size         | 4.4 MB           | 4.4 MB        | -----          |
Transferred Size      | 4.4 MB           | 2.9 MB        | 34.09%         |
DOM Loaded            | 1150 ms          | 743 ms        | 35.39%         |
Lighthouse Score      | 59/100           | 96/100        | 62.71%         |
First Paint           | 2.4 s            | 0.6 s         | 75.00%         |
Largest Paint         | 2.7 s            | 1.4 s         | 48.15%         |
Speed Index           | 2.4 s            | 1.0 s         | 58.33%         |
Time To Interactive   | 3.9 s            | 1.4 s         | 64.10%         |


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
java -jar target/quarkus-faces-runner.jar
```

Then open your web browser to http://localhost:8080/

### Docker

***
```
mvn clean package 
docker build -f docker/Dockerfile -t melloware/quarkus-faces . 
docker run -i --rm -p 8080:8080 melloware/quarkus-faces
```

OR run already pushed image:
```
docker run -i --rm -p 8080:8080 melloware/quarkus-faces
```