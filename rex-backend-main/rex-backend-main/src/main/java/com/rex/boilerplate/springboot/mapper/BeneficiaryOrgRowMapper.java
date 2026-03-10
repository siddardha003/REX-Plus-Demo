package com.rex.boilerplate.springboot.mapper;

import org.springframework.jdbc.core.RowMapper;

import com.rex.boilerplate.springboot.model.BeneficiaryOrgRow;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class BeneficiaryOrgRowMapper implements RowMapper<BeneficiaryOrgRow> {

    @Override
    public BeneficiaryOrgRow mapRow(ResultSet rs, int rowNum) throws SQLException {
        BeneficiaryOrgRow row = new BeneficiaryOrgRow();
        row.setPlatformId(rs.getObject("platform_id", Long.class));
        row.setBeneficiaryId(rs.getObject("beneficiary_id", Long.class));
        row.setPlatform(rs.getString("platform"));
        row.setEmployer(rs.getString("employer"));
        row.setListname(rs.getString("listname"));
        row.setLeavingdate(rs.getObject("leavingdate", LocalDate.class));
        row.setPersonnelnumbersystem(rs.getString("personnelnumbersystem"));
        row.setPersonnelnumberpayroll(rs.getString("personnelnumberpayroll"));
        row.setIdentity_tag(rs.getString("identity_tag"));
        row.setCompanysite(rs.getString("companysite"));
        row.setOrgunit(rs.getString("orgunit"));
        row.setCostcenter(rs.getString("costcenter"));
        return row;
    }
}
