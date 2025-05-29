package com.example.webflux_learning.section3.service;

import com.example.webflux_learning.section3.dto.CustomerDTO;
import com.example.webflux_learning.section3.entity.Customer;
import com.example.webflux_learning.section3.mapper.EntityDTOMapper;
import com.example.webflux_learning.section3.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Flux<CustomerDTO> getAllCustomer(){
        return this.customerRepository.findAll().map(EntityDTOMapper::toDTO);
    }

    public Flux<CustomerDTO> getAllCustomer(int pageNum, int pageSize){
        return this.customerRepository.findBy(PageRequest.of(pageNum - 1, pageSize))
                .map(EntityDTOMapper::toDTO);
    }


    public Mono<CustomerDTO> getCustomerById(Integer id){
        return this.customerRepository.findById(id).map(EntityDTOMapper::toDTO);
    }

    public Mono<CustomerDTO> saveCustomer(Mono<CustomerDTO> customer){
        return customer.map(EntityDTOMapper::toEntity)
                .flatMap(entity -> customerRepository.save(entity))
                .map(EntityDTOMapper::toDTO);
    }

    public Mono<CustomerDTO> updateCustomer(Integer id, Mono<CustomerDTO> customerDTOMono){
         return this.customerRepository.findById(id)
                 .flatMap(entity -> customerDTOMono)
                 .map(EntityDTOMapper::toEntity)
                 .doOnNext(c -> c.setId(id))
                 .flatMap(this.customerRepository::save)
                 .map(EntityDTOMapper::toDTO);
    }

    public Mono<ResponseEntity<Void>> deleteCustomerById(Integer id){
        return this.customerRepository.deleteCustomerById(id)
                .filter(b -> b)
                .map(b -> ResponseEntity.ok().<Void>build())
                .defaultIfEmpty(ResponseEntity.notFound().build())
                ;
    }

}
