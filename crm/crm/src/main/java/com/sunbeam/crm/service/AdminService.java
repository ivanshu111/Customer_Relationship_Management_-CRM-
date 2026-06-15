package com.sunbeam.crm.service;

import com.sunbeam.crm.dto.EmployeeResponseDto;
import com.sunbeam.crm.dto.CustomerResponseDto;

import java.util.List;

public interface AdminService {
   double getConversionRate();

    String getBestPerformingEmployee();

    List<EmployeeResponseDto> getAllEmployees();
    EmployeeResponseDto getEmployeeById(Integer id);

    List<CustomerResponseDto> getAllCustomers(); 
    
    List<CustomerResponseDto> getAllCustomersOfEmployee(Integer id);

}
