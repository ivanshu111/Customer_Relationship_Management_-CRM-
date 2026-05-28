package com.sunbeam.crm.controller;

import com.sunbeam.crm.dto.EmployeeResponseDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sunbeam.crm.service.AdminService;
import com.sunbeam.crm.service.LeadsService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

   private final AdminService adminService;
    private final LeadsService leadsService;
  
    @GetMapping("/analytics/conversion-rate")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Double> getConversionRate(){
        double conversionRate= adminService.getConversionRate();
        return ResponseEntity.ok(conversionRate);
    }

     @GetMapping("/analytics/best-employee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getBestPerformingEmployee(){
        String bestEmployee= adminService.getBestPerformingEmployee();

        if(bestEmployee == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(bestEmployee);
    }

    @GetMapping("/customers") 
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<?> getAllCustomers(){ 
        List<CustomerResponseDto> customers= adminService.getAllCustomers(); 
        return ResponseEntity.ok(customers); 
    }

    @GetMapping("/employee/{id}/customers") 
    @PreAuthorize("hasRole('ADMIN')") 
    public ResponseEntity<?> getAllCustomersOfEmployee(@PathVariable Integer id){ 
      List<CustomerResponseDto> customers= 
    adminService.getAllCustomersOfEmployee(id); 
      return ResponseEntity.ok(customers); 
    }
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>getAllEmployees(){
        List<EmployeeResponseDto> employees = adminService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?>getEmployeeById(@PathVariable Integer id){
        EmployeeResponseDto employee = adminService.getEmployeeById(id);
        return ResponseEntity.ok(employee);
    }


}
