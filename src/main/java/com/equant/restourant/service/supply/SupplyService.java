package com.equant.restourant.service.supply;

import com.equant.restourant.dao.supply.ISupplyDAO;
import com.equant.restourant.mapper.MapperFacade;
import com.equant.restourant.model.Order;
import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyService implements  ISupplyService{

    private ISupplyDAO supplyDAO;

    private MapperFacade mapperFacade;

    @Autowired
    public SupplyService(ISupplyDAO iSupplyDAO, MapperFacade mapperFacade) {
        this.supplyDAO = iSupplyDAO;
        this.mapperFacade = mapperFacade;
    }

    @Override
    public List<OrderDTOResponseStoreAndSupply> getAll() {
        List<Order> orders = supplyDAO.getAll();
        return mapperFacade.mapList(orders, OrderDTOResponseStoreAndSupply.class);
    }

    @Override
    public void executeOrder(Long id) {
        supplyDAO.executeOrder(id);

    }
}
