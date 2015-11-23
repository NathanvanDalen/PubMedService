/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.han.biocentre.PubMedService;

/**
 *
 * @author nathanvandalen
 */
import com.han.biocentre.PubMedService.health.PubMedServiceHealthCheck;
import com.han.biocentre.PubMedService.resources.PubMedResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.EnumSet;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import org.eclipse.jetty.servlets.CrossOriginFilter;

public class PubMedApplication extends Application<PubMedConfiguration> {

    public static void main(String[] args) throws Exception {
        new PubMedApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<PubMedConfiguration> bootstrap) {
        // nothing to do yet
    }
    /**
     * Run invokes a new resource object and adds all configuration parameters to this object.
     * A new healthcheck gets added to this object. This will always remain the same unless a public API is used instead of a local database.
     * @param configuration
     * @param environment 
     */
    @Override
    public void run(PubMedConfiguration configuration,
            Environment environment) {
        final PubMedResource resource = new PubMedResource(
                configuration.getHealthCheck(),
                configuration.getDefaultName(),
                configuration.getServiceIp(),
                configuration.getDbIp(),
                configuration.getDbPort(),
                configuration.getDbName(),
                configuration.getDbUser(),
                configuration.getDbPw()
        );
        final PubMedServiceHealthCheck healthCheck
                = new PubMedServiceHealthCheck(configuration.getHealthCheck(),configuration.getServiceName());
        environment.healthChecks().register("PubMedServiceHealthCheck", healthCheck);
        environment.jersey().register(resource);
            // Enable CORS headers
    final FilterRegistration.Dynamic cors =
        environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    // Configure CORS parameters
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    // Add URL mapping
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }


}
