package com.rex.boilerplate.springboot.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class DelegatedInvoiceResponseDTO {

    private DelegatedInvoiceMetaDTO meta;
    private List<DelegatedInvoiceDTO> data;

}