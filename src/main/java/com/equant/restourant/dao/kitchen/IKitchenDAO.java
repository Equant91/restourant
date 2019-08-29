package com.equant.restourant.dao.kitchen;

import com.equant.restourant.model.Order;
import com.equant.restourant.model.Product;
import com.equant.restourant.model.dto.OrderDTORequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IKitchenDAO {

    List<Order> findAll();

    Order insertOrder(OrderDTORequest orderDTORequest);

    public Order findById(long id);

    public void delete(Long id);

    public List<Product> findAllProducts();
}
