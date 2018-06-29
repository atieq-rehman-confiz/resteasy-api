package com.confiz.config;

import com.confiz.rest.Calculator;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Info;
import io.swagger.models.License;
import org.jboss.resteasy.plugins.interceptors.CorsFilter;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class APIApplication extends Application {
    private Set<Object> singletons = new HashSet<>(1);

    public APIApplication() {
        singletons.add(new Calculator());

        initializeCors();

        initializeSwaggerConfigurations();
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();

        resources.add(Calculator.class);
        resources.add(io.swagger.jaxrs.listing.ApiListingResource.class);
        resources.add(io.swagger.jaxrs.listing.SwaggerSerializers.class);

        return resources;
    }

    private void initializeCors() {
        CorsFilter corsFilter = new CorsFilter();
        corsFilter.getAllowedOrigins().add("*");
        corsFilter.setAllowedMethods("OPTIONS, GET, POST, DELETE, PUT, PATCH");
    }

    private void initializeSwaggerConfigurations() {
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion(PropertiesConfig.getProperty("api.version"));
        beanConfig.setSchemes(new String[]{PropertiesConfig.getProperty("api.scheme")});
        beanConfig.setHost(PropertiesConfig.getProperty("api.host"));
        beanConfig.setBasePath(PropertiesConfig.getProperty("api.basepath"));
        beanConfig.setResourcePackage(PropertiesConfig.getProperty("api.resource.package"));
        beanConfig.setScan(true);
        beanConfig.setPrettyPrint(true);
        beanConfig.setInfo(new Info()
                .title(PropertiesConfig.getProperty("api.title"))
                .version("1.0.0")
                .license(new License()
                        .name(PropertiesConfig.getProperty("api.license.name"))));
    }
}