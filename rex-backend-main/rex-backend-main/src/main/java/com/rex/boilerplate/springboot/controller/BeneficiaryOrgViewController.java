package com.rex.boilerplate.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rex.boilerplate.springboot.model.BeneficiaryOrgRow;
import com.rex.boilerplate.springboot.repository.BeneficiaryOrgViewRepository;

import java.util.List;

@RestController
@RequestMapping("/api/views/beneficiary-orgdata")
public class BeneficiaryOrgViewController {

    private final BeneficiaryOrgViewRepository repository;

    public BeneficiaryOrgViewController(BeneficiaryOrgViewRepository repository) {
        this.repository = repository;
    }

    /** GET /api/views/beneficiary-orgdata */
    @GetMapping
    public ResponseEntity<List<BeneficiaryOrgRow>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    /** GET /api/views/beneficiary-orgdata/by-platform/{platformId} */
    @GetMapping("/by-platform/{platformId}")
    public ResponseEntity<List<BeneficiaryOrgRow>> getByPlatform(@PathVariable Long platformId) {
        return ResponseEntity.ok(repository.findByPlatformId(platformId));
    }

    /** GET /api/views/beneficiary-orgdata/by-beneficiary/{beneficiaryId} */
    @GetMapping("/by-beneficiary/{beneficiaryId}")
    public ResponseEntity<List<BeneficiaryOrgRow>> getByBeneficiary(@PathVariable Long beneficiaryId) {
        return ResponseEntity.ok(repository.findByBeneficiaryId(beneficiaryId));
    }

    /** GET /api/views/beneficiary-orgdata/paged?limit=50&offset=0 */
    @GetMapping("/paged")
    public ResponseEntity<List<BeneficiaryOrgRow>> getPaged(
            @RequestParam(defaultValue = "50") int limit,
            @RequestParam(defaultValue = "0") int offset) {
        return ResponseEntity.ok(repository.findPaged(limit, offset));
    }
}

