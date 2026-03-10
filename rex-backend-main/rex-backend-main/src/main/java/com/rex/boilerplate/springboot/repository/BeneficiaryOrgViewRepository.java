package com.rex.boilerplate.springboot.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rex.boilerplate.springboot.mapper.BeneficiaryOrgRowMapper;
import com.rex.boilerplate.springboot.model.BeneficiaryOrgRow;

import java.util.List;
import java.util.Objects;

@Repository
public class BeneficiaryOrgViewRepository {

    private final JdbcTemplate jdbcTemplate;
    private final BeneficiaryOrgRowMapper rowMapper = new BeneficiaryOrgRowMapper();

    // Make the view name configurable; fallback to a sensible default.
    private final String viewName;

    public BeneficiaryOrgViewRepository(
            JdbcTemplate jdbcTemplate,
            @Value("${app.views.beneficiary-org:rexbase.beneficiary_to_platform_current}") String viewName
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.viewName = "rexbase.beneficiary_to_platform_current";
    }

    /** Fetch all rows from the view (no SQL duplication). */
    public List<BeneficiaryOrgRow> findAll() {
        final String sql = "SELECT * FROM " + viewName;
        return jdbcTemplate.query(sql, rowMapper);
    }

    /** Optional helper: filter by platform id using the view's projected column. */
    public List<BeneficiaryOrgRow> findByPlatformId(Long platformId) {
        Objects.requireNonNull(platformId, "platformId must not be null");
        final String sql = "SELECT * FROM " + viewName + " WHERE platform_id = ?";
        return jdbcTemplate.query(sql, rowMapper, platformId);
    }

    /** Optional helper: filter by beneficiary id using the view's projected column. */
    public List<BeneficiaryOrgRow> findByBeneficiaryId(Long beneficiaryId) {
        Objects.requireNonNull(beneficiaryId, "beneficiaryId must not be null");
        final String sql = "SELECT * FROM " + viewName + " WHERE beneficiary_id = ?";
        return jdbcTemplate.query(sql, rowMapper, beneficiaryId);
    }

    /** Optional helper: simple paging off the view. */
    public List<BeneficiaryOrgRow> findPaged(int limit, int offset) {
        final String sql = "SELECT * FROM " + viewName + " ORDER BY beneficiary_id LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, limit, offset);
    }
}
