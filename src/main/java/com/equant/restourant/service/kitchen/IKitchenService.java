package com.equant.restourant.service.kitchen;

import com.equant.restourant.model.Product;
import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.ProductDTOResponse;

import java.util.List;

public interface IKitchenService {

   Long addOrder(OrderDTORequest orderDTORequest);

   List<OrderDTOResponseKitchen> getAll();

  void remove(Long id);

   OrderDTOResponseKitchen getById(long id);

    List<ProductDTOResponse> getAllProducts();
}
