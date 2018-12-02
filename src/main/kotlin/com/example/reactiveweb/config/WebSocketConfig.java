package com.example.reactiveweb.config;

import com.example.reactiveweb.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Consumer;

@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter();
    }

    @Bean
    public WebSocketHandler webSocketHandler() {

        return webSocketSession -> {
            Flux<WebSocketMessage> greetingFlux = Flux.
                    <Greeting>generate(synchronousSink ->
                            synchronousSink.next(new Greeting("Hello @ " +
                                    Instant.now().toString())))
                    .map(g -> webSocketSession.textMessage(g.getText()))
                    .delayElements(Duration.ofSeconds(1))
                    .doFinally(signalType -> System.out.println("goodbye!!"));

                    return webSocketSession.send(greetingFlux);
        };
    }
}
