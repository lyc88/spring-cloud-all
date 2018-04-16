package com.ssp.feign;

import com.ssp.domain.PersonRequest;
import com.ssp.domain.PersonResponse;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * Person Feign api
 * @author: sunshaoping
 * @date: Create by in 下午2:43 2018/1/19
 */
@FeignClient("service-provider")
@RequestMapping("person")
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


}
