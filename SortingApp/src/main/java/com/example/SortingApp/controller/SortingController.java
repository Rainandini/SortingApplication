package com.example.SortingApp.controller;

import com.example.SortingApp.model.SortingRequest;
import com.example.SortingApp.services.SortingService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sort") 
public class SortingController {
    private final SortingService sortingService;

   
    public SortingController(SortingService sortingService) {
        this.sortingService = sortingService;
    }

    
    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Sorting API is running!");
    }

    
    @PostMapping("/process") 
    public ResponseEntity<List<Integer>> sort(@RequestBody SortingRequest request) {
        List<Integer> sortedNumbers = sortingService.sort(request);
        return ResponseEntity.ok(sortedNumbers);
    }
}
