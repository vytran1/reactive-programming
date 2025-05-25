package com.example.webflux_learning.tests.section2;

import com.example.webflux_learning.section2.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import reactor.test.StepVerifier;

public class Lec02ProductRepositoryTest extends AbstractTest{


    private static final Logger log = LoggerFactory.getLogger(Lec02ProductRepositoryTest.class);
    @Autowired
    private ProductRepository productRepository;



    @Test
    public void testFindProductInSpecificRange(){

        this.productRepository.findAllProductWhereInRange(1000L,2000L)
                .doOnNext(p -> log.info("{}",p))
                .as(StepVerifier::create)
                .expectNextCount(4)
                .expectComplete()
                .verify();

    }


    @Test
    public void testFindProductByPageable(){

        Sort sort = Sort.by("price");
        sort = sort.ascending();

        Pageable pageable = PageRequest.of(0,3,sort);


        this.productRepository.findBy(pageable)
                .doOnNext(p -> log.info("{}",p))
                .as(StepVerifier::create)
                .expectNextCount(3)
                .expectComplete()
                .verify();

    }

}
