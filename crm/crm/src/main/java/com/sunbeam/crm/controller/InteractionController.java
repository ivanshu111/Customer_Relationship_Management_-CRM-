package com.sunbeam.crm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.sunbeam.crm.dto.InteractionRequestDto;
import com.sunbeam.crm.service.InteractionService;

import java.util.List;

@RestController
@RequestMapping("/api/interaction")
@RequiredArgsConstructor
public class InteractionController {
   private final InteractionService interactionService;

   @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<?> createInteraction(@RequestBody InteractionRequestDto dto) {
        interactionService.createInteraction(dto);
        return ResponseEntity.ok("Interaction created successfully...!");
    }
  
}
