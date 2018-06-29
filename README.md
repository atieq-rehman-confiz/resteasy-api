# Sample App for RESTEasy API with Swagger-UI

This app uses RESTEasy API to expose REST web services on embeddy jetty web server.


## Prerequisites

* JDK 8
* Maven 4.0

## Dependencies

* Jetty
* REST Easy
* Swagger


## Configurations
* application.properties file has swagger related configurations which can be updated according to the needs

## Startup

* clean & build with mvn and run embedded jetty server as *jetty:run*

## How to extract swagger related stuff

Following steps are to be followed to extract swagger ui for integration with existing project.

* Configurable properties are placed in *application.properties*. You can use same or copy these properties in some existing configuration file.
* *com.confiz.config.PropertiesConfig.java* loads properties from local project required for swagger ui. Copy the same file or update APIApplication.java respectively to load required properties.
* *com.confiz.config.APIApplication.java* to be copied to the source
* All contents of *webapp* folder excluding *WEB-INF* are required to be copied to respective application's *webapp* folder.
* *API URL* in *index.html* at Line # 36 needs to be updated according to new environment.
* All contents of *web.xml* are required to be copied in respective application's *web.xml* or annotated accordingly.


Note: Currently application works only with **/** as root contact path, API path needs to be adjusted accordingly on following locations if any change in root context is required.
* web.xml ->Resteasy filter's *url-pattern*
* value of annotation *@ApplicationPath* in *APIApplication*
* Add/Update Rest resources in *APIApplication*
* Value of *api.basepath* in *application.properties* 
