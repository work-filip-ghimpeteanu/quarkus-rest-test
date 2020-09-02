package org.filip.config;

import io.quarkus.arc.config.ConfigProperties;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ConfigProperties(prefix = "application")
public interface Configs {

    @ConfigProperty(name = "name", defaultValue="...")
    String applicationName();

    @ConfigProperty(name = "controllers.number", defaultValue="0")
    String controllersNumber();
}
