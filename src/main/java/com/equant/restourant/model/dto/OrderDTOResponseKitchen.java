package com.equant.restourant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOResponseKitchen implements Serializable {

    private Long order_id;

    private Long product_id;

    private String name;

    private Boolean urgency;

    private Boolean executed;

    private Date date;

    private Integer weight;

}
