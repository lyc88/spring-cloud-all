package com.ssp;

import com.google.common.collect.Maps;
import org.springframework.cloud.bootstrap.BootstrapConfiguration;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.Map;

@BootstrapConfiguration
@Order
public class CustomPropertySourceLocator implements PropertySourceLocator {

    @Override
    public PropertySource<?> locate(Environment environment) {
        Map<String, Object> source = Maps.newLinkedHashMap();
        source.put("property.from.sample.custom.source", "this is custom property");
        return new MapPropertySource("customProperty", source);
    }

}