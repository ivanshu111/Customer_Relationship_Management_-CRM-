package com.sunbeam.crm.service;

import com.sunbeam.crm.dto.CustomerRequestDto;
import com.sunbeam.crm.dto.CustomerResponseDto;
import com.sunbeam.crm.entity.LeadStatus;

public interface CustomerService {
   void updateLeadStatus(Integer customerId, LeadStatus status);
    CustomerResponseDto updateCustomer(Integer customerId, CustomerRequestDto customerRequestDto);
     CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
     List<CustomerResponseDto> getMyCustomers();
}
