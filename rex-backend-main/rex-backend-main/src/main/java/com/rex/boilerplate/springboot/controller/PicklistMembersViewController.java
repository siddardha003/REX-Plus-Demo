package com.rex.boilerplate.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rex.boilerplate.springboot.model.PicklistMemberRow;
import com.rex.boilerplate.springboot.repository.PicklistMembersViewRepository;

import java.util.List;

@RestController
@RequestMapping("/api/views/picklist-members")
public class PicklistMembersViewController {

    private final PicklistMembersViewRepository repository;

    public PicklistMembersViewController(PicklistMembersViewRepository repository) {
        this.repository = repository;
    }

    /** GET /api/views/picklist-members */
    @GetMapping
    public ResponseEntity<List<PicklistMemberRow>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    /** GET /api/views/picklist-members/by-picklist/{picklistId} */
    @GetMapping("/by-picklist/{picklistId}")
    public ResponseEntity<List<PicklistMemberRow>> getByPicklist(@PathVariable Long picklistId) {
        return ResponseEntity.ok(repository.findByPicklistId(picklistId));
    }

    /** GET /api/views/picklist-members/by-type/{memberType}  (memberType: INTERNAL | EXERNAL) */
    @GetMapping("/by-type/{memberType}")
    public ResponseEntity<List<PicklistMemberRow>> getByMemberType(@PathVariable String memberType) {
        return ResponseEntity.ok(repository.findByMemberType(memberType));
    }

    /** GET /api/views/picklist-members/paged?limit=50&offset=0 */
    @GetMapping("/paged")
    public ResponseEntity<List<PicklistMemberRow>> getPaged(
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(repository.findPaged(limit, offset));
    }
}

