package com.rex.boilerplate.springboot.service;

import com.rex.boilerplate.springboot.dto.BeneficiaryDto;
import com.rex.boilerplate.springboot.dto.BeneficiaryFilterRequest;
import com.rex.boilerplate.springboot.dto.BeneficiaryPageResponse;
import com.rex.boilerplate.springboot.exceptions.NotFoundException;
import com.rex.boilerplate.springboot.mapper.BeneficiaryManualMapper;
import com.rex.boilerplate.springboot.model.Beneficiary;
import com.rex.boilerplate.springboot.repository.BeneficiaryRepository;
import com.rex.boilerplate.springboot.specification.BeneficiarySpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BeneficiaryServiceImpl implements BeneficiaryService {

        private final BeneficiaryRepository beneficiaryRepository;
        private final BeneficiaryManualMapper beneficiaryMapper;

        private static final Integer DEFAULT_PAGE_NUMBER = 0;
        private static final Integer DEFAULT_PAGE_SIZE = 10;
        private static final String DEFAULT_SORT_BY = "lastName";
        private static final String DEFAULT_SORT_DIRECTION = "asc";

        @Override
        @Transactional(readOnly = true)
        public BeneficiaryPageResponse fetchBeneficiaries(BeneficiaryFilterRequest filter) {
                int page = filter.getPage() != null ? filter.getPage() : DEFAULT_PAGE_NUMBER;
                int size = filter.getSize() != null ? filter.getSize() : DEFAULT_PAGE_SIZE;
                String sortBy = filter.getSortBy() != null ? filter.getSortBy() : DEFAULT_SORT_BY;
                Sort.Direction dir = "desc".equalsIgnoreCase(filter.getSortDirection())
                                ? Sort.Direction.DESC
                                : Sort.Direction.ASC;
                Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortBy));

                Specification<Beneficiary> spec = BeneficiarySpecification.getSpecifications(filter);
                Page<Beneficiary> pageResult = beneficiaryRepository.findAll(spec, pageable);

                List<BeneficiaryDto> dtos = pageResult.getContent().stream()
                                .map(beneficiaryMapper::beneficiaryToBeneficiaryDto)
                                .collect(Collectors.toList());

                return BeneficiaryPageResponse.builder()
                                .content(dtos)
                                .pageNumber(pageResult.getNumber())
                                .pageSize(pageResult.getSize())
                                .totalElements(pageResult.getTotalElements())
                                .totalPages(pageResult.getTotalPages())
                                .last(pageResult.isLast())
                                .build();
        }

        @Override
        @Transactional(readOnly = true)
        public BeneficiaryDto getBeneficiaryById(java.util.UUID id) {
                Beneficiary b = beneficiaryRepository.findByBeneficiaryId(id)
                                .orElseThrow(() -> new NotFoundException("Beneficiary not found with UUID: " + id));
                return beneficiaryMapper.beneficiaryToBeneficiaryDto(b);
        }
}
