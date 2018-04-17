package com.ssp.controller;

import com.ssp.domain.PersonRequest;
import com.ssp.domain.PersonResponse;
import com.ssp.feign.PersonFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * 消费者 controller
 * @author: sunshaoping
 * @date: Create by in 下午3:15 2018/1/19
 */

@RestController
@RequestMapping("consumer")
public class PersonConsumerController {

    private final PersonFeign personFeign;

    private final DiscoveryClient discoveryClient;

    @Autowired
    public PersonConsumerController(PersonFeign personFeign, DiscoveryClient discoveryClient) {
        this.personFeign = personFeign;
        this.discoveryClient = discoveryClient;
    }

    @PostMapping
    public PersonResponse save(@RequestBody PersonRequest personRequest) {

        return personFeign.save(personRequest);
    }

    @GetMapping
    public Collection<PersonResponse> findAll() {
        return personFeign.findAll();
    }


    @GetMapping("service-instances")
    public List<ServiceInstance> serviceInstances(String serviceId) {
        return discoveryClient.getInstances(serviceId);

    }


}
