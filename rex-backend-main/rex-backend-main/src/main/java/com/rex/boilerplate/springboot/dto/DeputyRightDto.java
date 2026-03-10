package com.rex.boilerplate.springboot.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeputyRightDto {
    private Long id;
    private String deputyRightDe;
    private String deputyRightEn;
}
