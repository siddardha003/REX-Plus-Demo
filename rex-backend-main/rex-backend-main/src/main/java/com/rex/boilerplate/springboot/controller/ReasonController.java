package com.rex.boilerplate.springboot.controller;

import com.rex.boilerplate.springboot.model.Reason;
import com.rex.boilerplate.springboot.repository.ReasonRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@RestController
@RequestMapping("/api/reasons")
public class ReasonController {

    private final ReasonRepository reasonRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ReasonController(ReasonRepository reasonRepository) {
        this.reasonRepository = reasonRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getReasons(
            @RequestParam("occasion_id") Long occasionId) {

        Long platformId = 101L;

        List<Reason> reasons =
                reasonRepository.findByPlatform_IdAndOccasion_Id(platformId, occasionId);

        List<Map<String,Object>> data = reasons.stream().map(r -> {

            Map<String,Object> map = new LinkedHashMap<>();

            map.put("reasonId", r.getId());

            try {
                map.put("reasonDisplay",
                        objectMapper.readValue(r.getDisplayText(), Map.class));
            } catch (Exception e) {
                map.put("reasonDisplay", r.getDisplayText());
            }

            map.put("occasionId", r.getOccasion().getId());

            return map;

        }).toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("platformId", platformId);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }
}