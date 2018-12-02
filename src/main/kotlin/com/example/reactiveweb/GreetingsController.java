package com.example.reactiveweb;

import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;

@RestController
public class GreetingsController {

    @GetMapping(value = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Publisher<Greeting> sseGreetings() {
        Flux<Greeting> delayElements = Flux.<Greeting>generate(
                sink -> sink.next(new Greeting("Hello World @ " +
                Instant.now().toString()))).delayElements(Duration.ofSeconds(1));

        return delayElements;
    }
}
