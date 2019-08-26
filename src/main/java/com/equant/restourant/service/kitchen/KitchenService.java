package com.equant.restourant.service.kitchen;

import com.equant.restourant.dao.kitchen.KitchenDAO;
import com.equant.restourant.mapper.IMapperFacade;
import com.equant.restourant.model.Order;
import com.equant.restourant.model.Product;
import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.ProductDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KitchenService implements IKitchenService {

    private KitchenDAO kitchenDAO;

    private IMapperFacade mapperFacade;

    @Autowired
    public KitchenService(KitchenDAO kitchenDAO, IMapperFacade mapperFacade) {
        this.kitchenDAO = kitchenDAO;
        this.mapperFacade = mapperFacade;
    }


    @Override
    public Long addOrder(OrderDTORequest orderDTORequest) {
        Order order = kitchenDAO.addOrder(orderDTORequest);
        return order.getId();
    }

    @Override
    public List<OrderDTOResponseKitchen> getAll() {
        return mapperFacade.mapList(kitchenDAO.getAll(), OrderDTOResponseKitchen.class);
    }

    @Override
    public void remove(Long id) {
        kitchenDAO.remove(id);
    }


    @Override
    public OrderDTOResponseKitchen getById(long id) {
        return mapperFacade.map(kitchenDAO.getById(id), OrderDTOResponseKitchen.class);
    }

    @Override
    public List<ProductDTOResponse> getAllProducts() {

        return mapperFacade.mapList(kitchenDAO.getAllProducts(), ProductDTOResponse.class);
    }
}
