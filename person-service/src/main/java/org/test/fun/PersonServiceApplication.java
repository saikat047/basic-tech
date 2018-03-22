package org.test.fun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.test.fun.service", "org.test.fun.repo"})
public class PersonServiceApplication {
    public static void main(String argv[]) {
        SpringApplication.run(PersonServiceApplication.class, argv);
    }
}
