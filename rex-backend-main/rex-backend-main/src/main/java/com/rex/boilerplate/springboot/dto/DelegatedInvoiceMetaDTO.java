package com.rex.boilerplate.springboot.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DelegatedInvoiceMetaDTO {

    private Long rexUserId;
    private String from;
    private String to;
    private int page;
    private int pageSize;

}