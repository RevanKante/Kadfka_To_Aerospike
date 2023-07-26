package com.micronaut;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Aerospike-kafka-payload",
                version = "1.0",
                description = "Payload-API"))
public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}