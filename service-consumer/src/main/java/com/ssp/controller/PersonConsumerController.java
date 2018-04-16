package com.ssp.controller;

import com.ssp.domain.PersonRequest;
import com.ssp.domain.PersonResponse;
import com.ssp.feign.PersonFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * 消费者 controller
 * @author: sunshaoping
 * @date: Create by in 下午3:15 2018/1/19
 */

@RestController
@RequestMapping("consumer")
public class PersonConsumerController {

    private final PersonFeign personFeign;

    @Autowired
    public PersonConsumerController(PersonFeign personFeign) {this.personFeign = personFeign;}

    @PostMapping
    public PersonResponse save(@RequestBody PersonRequest personRequest) {

        return personFeign.save(personRequest);
    }

    @GetMapping
    public Collection<PersonResponse> findAll() {
        return personFeign.findAll();
    }


}
