package com.sglp.sglp_api.core.mapper;

import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Converter String para boolean
        modelMapper.addConverter(new AbstractConverter<String, Boolean>() {
            @Override
            protected Boolean convert(String source) {
                return "Sim".equalsIgnoreCase(source);
            }
        });

        // Converter boolean para String
        modelMapper.addConverter(new AbstractConverter<Boolean, String>() {
            @Override
            protected String convert(Boolean source) {
                return source != null && source ? "Sim" : "Não";
            }
        });

        return modelMapper;
    }
}
