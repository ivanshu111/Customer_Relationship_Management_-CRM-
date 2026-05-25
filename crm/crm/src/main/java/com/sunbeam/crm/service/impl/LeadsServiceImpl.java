package com.sunbeam.crm.service.impl;

import org.springframework.stereotype.Service;

import com.sunbeam.crm.repository.LeadsRepository;
import com.sunbeam.crm.service.LeadsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LeadsServiceImpl implements LeadsService {
 
   private final LeadsRepository leadsRepository;
}
