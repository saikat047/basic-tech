package org.test.fun.service;


import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.test.fun.model.Person;

@RestController
@RequestMapping("/person")
public class PersonService {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.TEXT_PLAIN_VALUE
    )
    public ResponseEntity<String> create(@RequestBody @Valid Person person, UriComponentsBuilder uriBuilder) {
        person.setId(Long.toString(System.currentTimeMillis()));
        return ResponseEntity.created(uriBuilder.pathSegment("person").path(person.getId()).build().toUri()).build();
    }
}
