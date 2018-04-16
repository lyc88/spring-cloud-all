package com.ssp.repository;

import com.ssp.domain.Person;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Person
 * @author: sunshaoping
 * @date: Create by in 下午2:37 2018/1/19
 */
@Repository
public class PersonRepository {

    private final ConcurrentMap<Long, Person> repository = new ConcurrentHashMap<>();

    private final AtomicLong id = new AtomicLong();

    public void save(Person person) {
        person.setId(id.incrementAndGet());
        repository.put(person.getId(), person);
    }

    public Collection<Person> findAll(){
        return repository.values();
    }
}
