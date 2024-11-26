package com.coder.sanam.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationStrategy;
import org.springframework.web.accept.PathExtensionContentNegotiationStrategy;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        // Map extensions to media types
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("xml", MediaType.APPLICATION_XML);

        // Disable static resource lookup for extensions
//        PathExtensionContentNegotiationStrategy pathExtensionStrategy =
//                new PathExtensionContentNegotiationStrategy(mediaTypes);
//        pathExtensionStrategy.setUseRegisteredExtensionsOnly(true);

        configurer.defaultContentType(MediaType.APPLICATION_JSON)
                .mediaTypes(mediaTypes)
                .favorPathExtension(true);

    }

    @Override
    public  void configurePathMatch(PathMatchConfigurer configurer){
        configurer.setUseSuffixPatternMatch(true);
        configurer.setUseRegisteredSuffixPatternMatch(true);
    }
}
