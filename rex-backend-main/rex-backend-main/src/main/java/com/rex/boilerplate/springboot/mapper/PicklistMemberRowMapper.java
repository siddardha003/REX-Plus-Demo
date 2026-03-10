package com.rex.boilerplate.springboot.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.rex.boilerplate.springboot.model.PicklistMemberRow;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PicklistMemberRowMapper implements RowMapper<PicklistMemberRow> {

    @Override
    public PicklistMemberRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        PicklistMemberRow row = new PicklistMemberRow();
        row.setPicklistId(rs.getObject("picklist_id", Long.class));
        row.setMemberType(rs.getString("member_type"));   // e.g., "INTERNAL" or "EXERNAL"
        row.setListname(rs.getString("listname"));
        row.setCompanyname(rs.getString("companyname"));
        return row;
    }
}

