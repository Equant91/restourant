package com.equant.restourant.controller;

import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
import com.equant.restourant.service.supply.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/supply")
public class SupplyController {

    @Autowired
    ISupplyService supplyService;

    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public List<OrderDTOResponseStoreAndSupply> findAll(){
        return supplyService.getAll();
    }

    @RequestMapping(path = "/orders/{id}/executed", method = RequestMethod.POST)
    public void executeById(@PathVariable Long id){
        supplyService.executeOrder(id);

    }
}
