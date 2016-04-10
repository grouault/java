package com.book.chapter4.security.french;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

public class Configuration extends ResourceConfig {
    public Configuration() {
         register(RolesAllowedDynamicFeature.class);
    }
}