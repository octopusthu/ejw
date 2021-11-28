package com.octopusthu.ejw.sample.multireadrequest;

import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/multi-read")
public class MultiReadTestingController {

    @GetMapping
    Map<String, Object> get(@RequestParam Integer a) {
        return Map.of(
            "httpMethod", HttpMethod.GET.name(),
            "a", a
        );
    }

    @PostMapping
    Map<String, Object> post(@RequestParam Integer a, @RequestBody Message message) {
        return Map.of(
            "httpMethod", HttpMethod.POST.name(),
            "a", a,
            "b", message.getB(),
            "c", message.getC()
        );
    }

    @Data
    static class Message {
        private final Boolean b;
        private final Integer c;
    }
}
