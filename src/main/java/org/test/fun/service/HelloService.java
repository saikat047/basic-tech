package org.test.fun.service;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloService {

    @RequestMapping("/{name}")
    public String sayHello(@PathVariable("name") String name) {
        return String.format("Hello %s", name);
    }
}
