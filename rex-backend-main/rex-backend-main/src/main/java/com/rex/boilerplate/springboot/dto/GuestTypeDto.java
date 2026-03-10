package com.rex.boilerplate.springboot.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GuestTypeDto {
    private Long id;
    private String code;
    private String displayValue;
    private Boolean active;
}
