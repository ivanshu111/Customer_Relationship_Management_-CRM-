package com.sunbeam.crm.service;

import com.sunbeam.crm.dto.EmployeeResponseDto;

import java.util.List;

public interface AdminService {
   double getConversionRate();

    String getBestPerformingEmployee();
    List<InteractionResponseDto> getAllInteractions();
}
