package com.example.webflux_learning.section2.repositories;

import com.example.webflux_learning.section2.entity.Customer;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveCrudRepository<Customer,Integer> {

    Flux<Customer> findByName(String name);


    @Query("SELECT * FROM customer WHERE email LIKE $1")
    Flux<Customer> findAllCustomerWithEmailsEndingWithKey(String endingKey);


}
