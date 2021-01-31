package com.octopusthu.ejw.sample.springrestdocs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author octopusthu
 */
@RestController
public class HelloJavaRestController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello Java!";
    }

}
