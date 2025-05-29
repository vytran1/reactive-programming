package com.example.webflux_learning.section3.controller;

import com.example.webflux_learning.section3.dto.CustomerDTO;
import com.example.webflux_learning.section3.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @GetMapping()
    public Flux<CustomerDTO> allCustomers(){
        return this.customerService.getAllCustomer();
    }

    @GetMapping("/paginated")
    public Mono<List<CustomerDTO>> allCustomers(@RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum, @RequestParam(defaultValue = "3", value = "pageSize") Integer pageSize){
        return this.customerService.getAllCustomer(pageNum,pageSize).collectList();
    }


    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> getCustomerById(@PathVariable("id") Integer id){
        return this.customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                ;
    }

    @PostMapping
    public Mono<CustomerDTO> saveCustomer(@RequestBody Mono<CustomerDTO> mono){
        return this.customerService.saveCustomer(mono);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> updateCustomer(@PathVariable("id") Integer id,@RequestBody Mono<CustomerDTO> mono){
        return this.customerService.updateCustomer(id,mono) .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                ;
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable("id") Integer id){
        return this.customerService.deleteCustomerById(id);
    }

}
