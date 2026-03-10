package com.rex.boilerplate.springboot.repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.rex.boilerplate.springboot.mapper.PicklistMemberRowMapper;
import com.rex.boilerplate.springboot.model.PicklistMemberRow;

import java.util.List;
import java.util.Objects;

/**
 * Reads from the existing database VIEW only.
 * No duplication of the view's SQL here — we select from the view by name.
 *
 * Configure the view name via property:
 *   app.views.picklist-members=rexbase.picklist_members
 * If not set, defaults to "rexbase.picklist_members".
 */
@Repository
public class PicklistMembersViewRepository {

    private final JdbcTemplate jdbcTemplate;
    private final PicklistMemberRowMapper rowMapper = new PicklistMemberRowMapper();

    private final String viewName;

    public PicklistMembersViewRepository(
            JdbcTemplate jdbcTemplate,
            @Value("${app.views.picklist-members:rexbase.picklist_members}") String viewName
    ) {
        this.jdbcTemplate = jdbcTemplate;
        this.viewName = "rexbase.picklist_members";
    }

    /** Fetch all rows from the view. */
    public List<PicklistMemberRow> findAll() {
        final String sql = "SELECT * FROM " + viewName;
        return jdbcTemplate.query(sql, rowMapper);
    }

    /** Filter by picklist id (column exposed by the view). */
    public List<PicklistMemberRow> findByPicklistId(Long picklistId) {
        Objects.requireNonNull(picklistId, "picklistId must not be null");
        final String sql = "SELECT * FROM " + viewName + " WHERE picklist_id = ?";
        return jdbcTemplate.query(sql, rowMapper, picklistId);
    }

    /** Optional: filter by member type (“INTERNAL” or “EXERNAL”) if needed. */
    public List<PicklistMemberRow> findByMemberType(String memberType) {
        Objects.requireNonNull(memberType, "memberType must not be null");
        final String sql = "SELECT * FROM " + viewName + " WHERE member_type = ?";
        return jdbcTemplate.query(sql, rowMapper, memberType);
    }

    /** Simple paging off the view. */
    public List<PicklistMemberRow> findPaged(int limit, int offset) {
        final String sql = "SELECT * FROM " + viewName + " ORDER BY picklist_id LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, limit, offset);
    }
}
