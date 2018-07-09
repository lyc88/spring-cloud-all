package com.ssp.feign;

import com.ssp.domain.PersonRequest;
import com.ssp.domain.PersonResponse;
import feign.hystrix.FallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Collections;

/**
 * Person Feign api
 * @author: sunshaoping
 * @date: Create by in 下午2:43 2018/1/19
 */
@RequestMapping("person")
@FeignClient(name = "service-provider", fallbackFactory = PersonFeign.PersonFeignFallBackFactory.class)
public interface PersonFeign {

    /**
     * 保存
     */
    @PostMapping
    PersonResponse save(PersonRequest person);

    /**
     * 查询全部
     */
    @GetMapping
    Collection<PersonResponse> findAll();


    @Component
    class PersonFeignFallBackFactory implements FallbackFactory<PersonFeign> {

        @Override
        public PersonFeign create(Throwable cause) {
            return new PersonFeignFallBack();
        }
    }
    class PersonFeignFallBack implements PersonFeign {

        PersonFeignFallBack() {
            System.out.println("PersonFeignFallBack 实例化");
        }

        @Override
        public PersonResponse save(PersonRequest person) {
            return create();
        }

        private PersonResponse create() {
            PersonResponse personResponse = new PersonResponse();
            personResponse.setName("熔断保护-账户");
            return personResponse;
        }

        @Override
        public Collection<PersonResponse> findAll() {
            return Collections.singleton(create());
        }
    }
}
