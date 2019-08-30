package com.equant.restourant;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication
public class RestourantApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestourantApplication.class, args);
    }
    @Bean
    public MapperFactory mapperFactory(){
        return new DefaultMapperFactory.Builder()
                .constructorResolverStrategy(null)
                .build();
    }

}
