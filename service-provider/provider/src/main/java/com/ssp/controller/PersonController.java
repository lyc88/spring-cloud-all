package com.ssp.controller;

import com.ssp.domain.PersonRequest;
import com.ssp.domain.PersonResponse;
import com.ssp.feign.PersonFeign;
import com.ssp.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * person controller
 * @author: sunshaoping
 * @date: Create by in 下午2:36 2018/1/19
 */
@RestController
@RequestMapping("/person")
public class PersonController implements PersonFeign {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {this.personService = personService;}

    @PostMapping
    @Override
    public PersonResponse save(@RequestBody PersonRequest personRequest) {

        return personService.save(personRequest);
    }

    @GetMapping
    @Override
    public Collection<PersonResponse> findAll() {
        return personService.findAll();
    }
}
