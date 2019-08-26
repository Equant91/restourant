package com.equant.restourant.dao.store;

import com.equant.restourant.model.Order;

import java.util.List;

public interface IStoreDAO {

    public List<Order> getAll();

    public void executeOrder(Long id);
}
