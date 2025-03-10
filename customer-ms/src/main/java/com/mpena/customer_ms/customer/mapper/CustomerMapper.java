package com.mpena.customer_ms.customer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.mpena.customer_ms.customer.dto.CustomerCreateDTO;
import com.mpena.customer_ms.customer.dto.CustomerResponseDTO;
import com.mpena.customer_ms.customer.entity.Customer;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerResponseDTO toDTO(Customer customerEntity);
    Customer toEntity(CustomerCreateDTO customerCreateDTO);
}
