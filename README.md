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

You can learn more about Quarkus OIDC at - OIDC [OIDC](https://quarkus.io/guides/security-oidc-code-flow-authentication-tutorial)


### Back Channel Logout
This example also demonstrates how to implement `Back-Channel Logout` so that when you logout a session in Keycloak
it will automatically contact Quarkus to log the user out.

Before you start the server in Dev mode, you need to modify the `oidc-auth-realm.json` file so you can communicate
from the Dev Keycloak Docker image to your PC running the Quarkus server.

We can't use `localhost` because Keycloak will try to talk to itself and that won't work.

So, in the `oidc-auth-realm.json` file look for the line
```
"backchannel.logout.url" : "http://192.168.1.233:8081/back-channel-logout"
```

and change the `host:port` to whichever IP and port you are running the Quarkus app on.

Once that is saved, then you can  start quarkus with `mvn quarkus:dev`

Thanks to https://github.com/pjgg/Back-channel-logout-demo for the great example on how to use Quarkus and Keycloak with `Back-Channel Logout`

Once you visit http://localhost:8081 and try to access any of the links on the left menu, you'll be asked to log into Keycloak.

You can use `alice:alice` or `bob:bob` as the username and password.

Then in the web application you will see a `Logout` link displayed as mention in the previous section.

Now, to test the logout:

1. Visit http://localhost:51521/ 
2. Log in using `admin:admin`
3. Select the `oidc-auth-test` realm from the dropdown
4. Select `Sessions` from the left hand menu
5. You'll see a list of users logged into the realm (in this case `alice` or `bob`)
6. Click the 3 dots on the far right for the user you want to log out and select `Sign Out` from the menu
7. In the console running your Quarkus demo, you should see a log line:
`2023-09-29 14:38:15,907 DEBUG [io.qua.oid.run.BackChannelLogoutHandler] (vert.x-eventloop-thread-2) Back channel logout request for the tenant Default received`
8. When you attempt to refresh the page or go to another link on the left menu, you will now be asked to log in again.

If this happens, then the logout has worked.

**NOTE**: If you try to use the `Sign out all session` option under the `Action` menu on the session page, nothing will happen when you click the `Submit` button.
There is an open issue with Keycloak about that: https://github.com/keycloak/keycloak/issues/23604



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

Then open your web browser to http://localhost:8081/

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

