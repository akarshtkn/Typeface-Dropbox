package com.typeface.dropbox.commons.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Set the matching strategy to strict to avoid unexpected mappings
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        // You can add custom mappings or converters here if needed
        // For example:
        // modelMapper.addConverter(new YourCustomConverter());

        return modelMapper;
    }
}
