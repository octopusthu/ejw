package com.octopusthu.ejw.demo.web.back.controller;

import com.octopusthu.ejw.web.back.controller.BackBaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoBackController extends BackBaseController {
    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

}
