package com.equant.restourant.dao.supply;

import com.equant.restourant.model.Order;

import java.util.List;

public interface ISupplyDAO {

    public List<Order> getAll();

    public void executeOrder(Long id);
}
