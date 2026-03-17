package com.rex.boilerplate.springboot.controller;

import com.rex.boilerplate.springboot.model.Occasions;
import com.rex.boilerplate.springboot.repository.OccasionRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@RequestMapping("/api/invoices/occasions")
public class OccasionController {

    private final OccasionRepository occasionRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OccasionController(OccasionRepository occasionRepository) {
        this.occasionRepository = occasionRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getOccasions() throws Exception {

        Long platformId = 101L;

        List<Occasions> occasions = occasionRepository.findByPlatform_Id(platformId);

        List<Map<String,Object>> data = occasions.stream().map(o -> {
            Map<String,Object> map = new LinkedHashMap<>();

            map.put("occasionId", o.getId());

            try {
                map.put("occasionDisplay",
                        objectMapper.readValue(o.getDisplayText(), Map.class));
            } catch (Exception e) {
                map.put("occasionDisplay", o.getDisplayText());
            }

            return map;

        }).toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("platformId", platformId);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }
}