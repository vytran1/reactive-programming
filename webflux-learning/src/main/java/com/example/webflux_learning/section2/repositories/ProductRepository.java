package com.example.webflux_learning.section2.repositories;

import com.example.webflux_learning.section1.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProductRepository extends ReactiveCrudRepository<Product,Integer> {


    @Query("SELECT * FROM Product WHERE price between $1 AND $2")
    Flux<Product> findAllProductWhereInRange(Long start,Long end);

    Flux<Product> findBy(Pageable pageable);

}
