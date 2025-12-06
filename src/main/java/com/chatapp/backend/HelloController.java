package com.chatapp.backend;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static reactor.netty.http.HttpConnectionLiveness.log;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        log.info("Hello endpoint was called");
        return "hello";
    }
}
