package com.example.webflux_learning.tests.section2;


import com.example.webflux_learning.section2.entity.Customer;
import com.example.webflux_learning.section2.repositories.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.test.StepVerifier;

public class Lec01CustomerRepositoryTest  extends AbstractTest{

    private static final Logger log = LoggerFactory.getLogger(Lec01CustomerRepositoryTest.class);

    @Autowired
    private CustomerRepository customerRepository;


    @Test
    public void findAll(){
        this.customerRepository.findAll().doOnNext(c -> log.info("{}",c))
                .as(StepVerifier::create)
                .expectNextCount(10)
                .expectComplete()
                .verify();
    }


    @Test
    public void findByName(){
        this.customerRepository.findByName("jake").doOnNext(c -> log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("jake@gmail.com",c.getEmail()))
                .expectComplete()
                .verify();
    }


    @Test
    public void findByEmailEndingWith(){
        this.customerRepository.findAllCustomerWithEmailsEndingWithKey("%ke@gmail.com")
                .doOnNext(c -> log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("mike@gmail.com",c.getEmail()))
                .assertNext(c -> Assertions.assertEquals("jake@gmail.com",c.getEmail()))
                .expectComplete()
                .verify();
    }


    @Test
    public void insertAndDeleteCustomer(){
        //Arrange
        var customer = new Customer();
        customer.setEmail("test@gmail.com");
        customer.setName("testting");
        this.customerRepository.save(customer).doOnNext(c -> log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("test@gmail.com",c.getEmail()))
                .expectComplete()
                .verify();

        this.customerRepository.count()
                .as(StepVerifier::create)
                .expectNext(11L)
                .expectComplete()
                .verify();

        this.customerRepository.deleteById(11)
                .then(this.customerRepository.count())
                .as(StepVerifier::create)
                .expectNext(10L)
                .expectComplete()
                .verify();

    }

    @Test
    public void updateCustomer(){
        this.customerRepository.findByName("ethan")
                .doOnNext(c -> c.setName("noel"))
                .flatMap(c -> this.customerRepository.save(c))
                .doOnNext(c -> log.info("{}",c))
                .as(StepVerifier::create)
                .assertNext(c -> Assertions.assertEquals("noel",c.getName()))
                .expectComplete()
                .verify();
    }

}
