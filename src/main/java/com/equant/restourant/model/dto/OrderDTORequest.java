package com.equant.restourant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTORequest {

    private String name;

    private Integer weight;

    private Boolean urgency;
}
