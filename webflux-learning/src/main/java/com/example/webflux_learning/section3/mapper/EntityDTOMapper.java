package com.example.webflux_learning.section3.mapper;

import com.example.webflux_learning.section3.dto.CustomerDTO;
import com.example.webflux_learning.section3.entity.Customer;

public class EntityDTOMapper {

    public static Customer toEntity(CustomerDTO customerDTO){
        var customer = new Customer();
        customer.setId(customer.getId());
        customer.setName(customer.getName());
        customer.setEmail(customer.getEmail());
        return customer;
    }

    public static CustomerDTO toDTO(Customer customer){
        return new CustomerDTO(customer.getId(),customer.getName(),customer.getEmail());
    }

}
