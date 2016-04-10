package fr.exagone.ws.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import fr.exagone.ws.filter.CORSResponseFilter;

/**
 * JerseySpringApplication -
 *
 */
@ApplicationPath("/api/*")
public class JerseySpringApplication extends ResourceConfig {
    public JerseySpringApplication() {
        packages("fr.exagone.ws.ressources", "fr.exagone.ws.filter");
        register(JacksonFeature.class);
        register(MultiPartFeature.class);
        register(CORSResponseFilter.class);
        property(ServerProperties.TRACING, "ALL");
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
    }
}