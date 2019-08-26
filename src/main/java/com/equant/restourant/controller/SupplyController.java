package com.equant.restourant.controller;

import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
import com.equant.restourant.service.supply.ISupplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/supply")
public class SupplyController {

    @Autowired
    ISupplyService supplyService;

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public List<OrderDTOResponseStoreAndSupply> findAll(){
        return supplyService.getAll();
    }

    @RequestMapping(path = "/execute", method = RequestMethod.POST)
    public void executeById(@RequestBody Long id){
        supplyService.executeOrder(id);

    }
}
