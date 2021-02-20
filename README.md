[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

Quarkus Faces Comparison
==========================

[![Quarkus Faces Logo](https://github.com/melloware/quarkus-faces/blob/main/src/site/QuarkusFaces.svg)](https://github.com/melloware/quarkus-faces)

### Overview

The goal was to take an out of the box JSF application (PrimeFaces Showcase) and run it in both a Java EE Server and in [Quarkus](https://quarkus.io/).
In the process of doing this I also added various optimization tricks I have learned over the years for optimizing a JSF application.

### Environment
- OpenJDK 11.0.10
- JBoss Wildfly 18.0.1
- Quarkus 1.11.2
- JSF Production Mode

### Optimizations
- Apache MyFaces instead of Jakarta Mojarra
- PrimeFaces [MOVE_SCRIPTS_TO_BOTTOM](https://primefaces.github.io/primefaces/10_0_0/#/gettingstarted/configuration?id=configuration)
- OmniFaces [GzipResponseFilter](https://showcase.omnifaces.org/filters/GzipResponseFilter)
- OmniFaces [CombinedResourceHandler](https://showcase.omnifaces.org/resourcehandlers/CombinedResourceHandler)
- PrimeFaces Extensions [CombinedResourceHandler Helper](https://github.com/primefaces-extensions/primefaces-extensions/issues/293) 
- PrimeFaces Extensions [Optimizer](https://github.com/primefaces-extensions/resources-optimizer-maven-plugin)
- jQuery [Hide Page Until Complete](https://stackoverflow.com/questions/9550760/hide-page-until-everything-is-loaded-advanced/28129691#28129691)

### Metrics

The following client and server metrics were captured while hitting the exact same page [/datatable.basic.xhtml](https://www.primefaces.org/showcase/ui/data/datatable/basic.xhtml)
Using `Incognito Mode` and pressing CTRL+F5 so it forced the browser to load all resources from the server with nothing cached.

Metric                | JBoss WildFly EE | Quarkus       | Improvement    |
----------------------| ---------------  | ------------- |----------------|
Cold Startup          | 13.7 s           | 1.67 s        | 87.7%          |
Memory Used           | 140 MB           | 31 MB         | 77.8%          |
HTTP Requests         | 97               | 82            | 15.4%          |
Resource Size         | 5.4 MB           | 5.4 MB        | -----          |
Transferred Size      | 5.4 MB           | 3.0 MB        | 44.4%          |
DOM Loaded            | 1650 ms          | 599 ms        | 63.7%          |
Lighthouse Score      | 38/100           | 94/100        | 59.5%          |
First Paint           | 4.0 s            | 0.6 s         | 85.0%          |
Largest Paint         | 5.1 s            | 1.5 s         | 70.5%          |
Speed Index           | 4.0 s            | 0.9 s         | 77.5%          |
Time To Interactive   | 6.0 s            | 1.5 s         | 75.0%          |
Total Blocking Time   | 20 ms            | 10 ms         | 50.0%          |

### Development

***
To run the example in Dev mode:

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces-showcase
mvn clean compile quarkus:dev
```

Then open your web browser to http://localhost:8080/

### Production

***
To run the example in HotSpot Production mode (GraalVM native-image not supported):

```
git clone https://github.com/melloware/quarkus-faces
cd quarkus-faces-showcase
mvn clean package
java -jar target/showcase-10.0.0-runner.jar
```

Then open your web browser to http://localhost:8080/