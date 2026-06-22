package com.sunbeam.crm.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.crm.dto.CustomerRequestDto;
import com.sunbeam.crm.dto.CustomerResponseDto;
import com.sunbeam.crm.service.CustomerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
   private final CustomerService customerService;

     @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerRequestDto dto){
        CustomerResponseDto customer= customerService.addCustomer(dto);
        return ResponseEntity.ok(customer);
    }

      @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')")
    public ResponseEntity<?> updateCustomer(@PathVariable Integer id, @Valid @RequestBody CustomerRequestDto dto){
        CustomerResponseDto customer = customerService.updateCustomer(id, dto);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/my") 
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')") 
    public ResponseEntity<?> getMyCustomers(){ 
        List<CustomerResponseDto> customers = customerService.getMyCustomers(); 
        return ResponseEntity.ok(customers); 
    }

    @GetMapping("/interested") 
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYEE')") 
    public ResponseEntity<?> getInterestedCustomers(){
         List<CustomerResponseDto> customers = customerService.getInterestedCustomers();
          return ResponseEntity.ok(customers); 
    }
}
