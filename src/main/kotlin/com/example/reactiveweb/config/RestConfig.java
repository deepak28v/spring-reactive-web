package com.example.reactiveweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Configuration
public class RestConfig {

    @Bean
    RouterFunction<ServerResponse> routes(){
        return RouterFunctions.route(
                (RequestPredicates.GET("/greetings")),
                    serverRequest ->
                        ServerResponse.ok().body(Flux.just("Hello World !!"), String.class));
    }
}
