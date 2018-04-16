package com.ssp.service;

import com.ssp.domain.Person;
import com.ssp.domain.PersonRequest;
import com.ssp.domain.PersonResponse;
import com.ssp.repository.PersonRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Person
 * @author: sunshaoping
 * @date: Create by in 下午2:37 2018/1/19
 */
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonResponse save(PersonRequest personRequest) {
        Person person = new Person();
        BeanUtils.copyProperties(personRequest, person);
        personRepository.save(person);
        return getPersonResponse(person);
    }

    private PersonResponse getPersonResponse(Person person) {
        PersonResponse response = new PersonResponse();
        BeanUtils.copyProperties(person, response);
        return response;
    }

    public Collection<PersonResponse> findAll() {
        return personRepository.findAll()
                .stream()
                .map(this::getPersonResponse)
                .collect(Collectors.toList());
    }
}

