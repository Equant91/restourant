package com.equant.restourant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTORequest {

    @NotEmpty
    private String name;
    @NotNull
    private Integer weight;
    @NotNull
    private Boolean urgency;
}
