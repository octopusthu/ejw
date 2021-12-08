package com.octopusthu.ejw.sample.multireadrequest;

import java.util.Map;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi-read")
public class MultiReadRequestTestingController {

    @GetMapping
    Map<String, Object> get(@RequestParam Integer a) {
        return Map.of(
            "httpMethod", HttpMethod.GET.name(),
            "a", a
        );
    }

    @PostMapping(consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    Map<String, Object> jsonPost(@RequestParam Integer a, @RequestParam String b, @RequestParam Integer c) {
        return Map.of(
            "httpMethod", HttpMethod.POST.name(),
            "a", a,
            "b", b,
            "c", c
        );
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    Map<String, Object> jsonPost(@RequestParam Integer a, @RequestBody Message message) {
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
