package com.sunbeam.crm.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.sunbeam.crm.entity.Role;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.sunbeam.crm.dto.CustomerResponseDto;
import com.sunbeam.crm.dto.EmployeeResponseDto;
import com.sunbeam.crm.entity.Customer;
import com.sunbeam.crm.entity.LeadStatus;
import com.sunbeam.crm.entity.Users;
import com.sunbeam.crm.repository.CustomerRepository;
import com.sunbeam.crm.repository.InteractionRepository;
import com.sunbeam.crm.repository.LeadsRepository;
import com.sunbeam.crm.repository.UserRepository;
import com.sunbeam.crm.service.AdminService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final InteractionRepository interactionRepository;
    private final LeadsRepository leadsRepository;
    private final ModelMapper modelMapper;

     public double getConversionRate() {
        long closedDeals= leadsRepository.countByStatus(LeadStatus.valueOf("CLOSED"));
        long totalLeads= leadsRepository.count();

        if(totalLeads == 0) return 0.0;

        double rawRate= ((double)  closedDeals / totalLeads )*100;
        return BigDecimal.valueOf(rawRate)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public String getBestPerformingEmployee() {
        return leadsRepository.findBestPerformingEmployee().stream()
                .findFirst()
                .map(user -> user.getName())
                .orElse("No top performing employee found");
    }

      private EmployeeResponseDto mapToDto(Users user) {
        EmployeeResponseDto dto = modelMapper.map(user, EmployeeResponseDto.class);
        if (user.getCreatedAt() != null) {
            dto.setCreated_at(user.getCreatedAt().format(DateTimeFormatter.ISO_DATE_TIME));
        }
        return dto;
    }

    private CustomerResponseDto mapToCustomerDto(Customer customer) {
        CustomerResponseDto dto = modelMapper.map(customer, CustomerResponseDto.class);
        if (customer.getAssignedTo() != null) {
            dto.setAssignedToName(customer.getAssignedTo().getName());
        }
        return dto;
    }

    @Override
    public List<EmployeeResponseDto> getAllEmployees(){
         List<Users> users = userRepository.findByRole(Role.EMPLOYEE);
         return users.stream().map(user->mapToDto(user)).collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDto getEmployeeById(Integer id){
         Users user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Employee not found."));
         return mapToDto(user);
    }
  
}
