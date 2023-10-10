package com.example.bankingManagementSystem.converter;

import com.example.bankingManagementSystem.dto.CustomerDto;
import com.example.bankingManagementSystem.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    @Autowired
    ModelMapper modelMapper;


    public Customer dtoToEntity(CustomerDto customerDto) {
        return modelMapper.map(customerDto, Customer.class);
    }

    public CustomerDto entityToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

}
