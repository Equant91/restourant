package com.equant.restourant.dao.kitchen;

import com.equant.restourant.model.Order;
import com.equant.restourant.model.Product;
import com.equant.restourant.model.dto.OrderDTORequest;

import java.util.List;

public interface IKitchenDAO {

    List<Order> getAll();

    Order addOrder(OrderDTORequest orderDTORequest);

    public Order getById(long id);

    public void remove(Long id);

    public List<Product> getAllProducts();
}
