package com.equant.restourant.service.store;

import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;

import java.util.List;

public interface IStoreService {

    List<OrderDTOResponseStoreAndSupply> getAll();

    void executeOrder(Long id);
}
