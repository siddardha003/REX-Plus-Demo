package com.rex.boilerplate.springboot.controller;

import com.rex.boilerplate.springboot.model.Events;
import com.rex.boilerplate.springboot.repository.EventRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/invoices/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getEvents() {

        Long platformId = 101L; // TODO: replace with JWT platformId

        List<Events> events = eventRepository.findByPlatform_Id(platformId);

        List<Map<String, Object>> data = events.stream().map(e -> {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("eventId", e.getId());
            map.put("eventName", e.getEventName());
            return map;
        }).toList();

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("platformId", platformId);
        response.put("data", data);

        return ResponseEntity.ok(response);
    }
}