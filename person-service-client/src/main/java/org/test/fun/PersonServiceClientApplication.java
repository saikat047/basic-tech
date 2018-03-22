package org.test.fun;

import java.io.Console;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import org.test.fun.model.Person;

@SpringBootApplication(scanBasePackages = {"org.test.fun.config"})
public class PersonServiceClientApplication implements CommandLineRunner {

    public static void main(String argv[]) {
        SpringApplication.run(PersonServiceClientApplication.class, argv);
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void run(String... args) {
        Console console = System.console();
        if (console == null && args.length == 0) {
            throw new IllegalStateException("No attached console found and no name argument given");
        }

        String serviceUrl = System.getenv("PERSON_SERVICE_URL");
        if (serviceUrl == null) {
            throw new IllegalStateException("No person-service URL set");
        }

        if (console != null) {
            String name = console.readLine("Insert person name:");
            while (name != null) {
                Person p = createPerson(name);
                URI personUrl = restTemplate.postForLocation(serviceUrl, new HttpEntity<>(p));
                System.out.println("Created person: " + personUrl);
                name = console.readLine("Insert person name:");
            }
        }

        for (String arg : args) {
            Person p = createPerson(arg);
            URI personUrl = restTemplate.postForLocation(serviceUrl, p);
            System.out.println("Created person: " + personUrl);
        }
    }

    private Person createPerson(String name) {
        Person p = new Person();
        p.setFirstName(name);
        p.setLastName("Generated");
        return p;
    }
}
