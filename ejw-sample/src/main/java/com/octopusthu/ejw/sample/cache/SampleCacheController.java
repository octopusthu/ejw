package com.octopusthu.ejw.sample.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sample/cache")
public class SampleCacheController {

    @Autowired
    SampleCacheService service;

    @GetMapping("/get/{key}")
    public Integer get(@PathVariable int key) {
        return service.get(key);
    }

    @RequestMapping("/clear-cache")
    public Boolean clearCache() {
        return service.clearCache();
    }

}
