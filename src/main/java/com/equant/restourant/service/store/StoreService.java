package com.equant.restourant.service.store;

import com.equant.restourant.dao.store.IStoreDAO;
import com.equant.restourant.dao.store.StoreDAO;
import com.equant.restourant.mapper.MapperFacade;
import com.equant.restourant.model.Order;
import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements IStoreService {

    private IStoreDAO storeDAO;

    private MapperFacade mapperFacade;

    @Autowired
    public StoreService(StoreDAO storeDAO, MapperFacade mapperFacade) {
        this.storeDAO = storeDAO;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OrderDTOResponseStoreAndSupply> getAll() {
        List<Order> orders = storeDAO.getAll();
        return mapperFacade.mapList(orders, OrderDTOResponseStoreAndSupply.class);
    }

    @Override
    public void executeOrder(Long id) {
        storeDAO.executeOrder(id);
    }
}
