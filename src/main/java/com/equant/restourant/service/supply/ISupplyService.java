package com.equant.restourant.service.supply;

import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;

import java.util.List;

public interface ISupplyService {


    List<OrderDTOResponseStoreAndSupply> getAll();

    void executeOrder(Long id);
}
