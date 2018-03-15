package org.test.fun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.test.fun.service")
public class TestApplication {
    public static void main(String argv[]) {
        SpringApplication.run(TestApplication.class, argv);
    }
}
