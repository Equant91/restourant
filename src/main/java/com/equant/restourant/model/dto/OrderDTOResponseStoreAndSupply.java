package com.equant.restourant.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOResponseStoreAndSupply {

    private Long order_id;

    private Long product_id;

    private String name;

    private Date date;

    private Integer weight;

}
