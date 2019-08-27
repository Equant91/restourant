package com.equant.restourant.controller;

import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
import com.equant.restourant.service.store.IStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/store")
public class StoreController {

    @Autowired
    IStoreService iStoreService;

    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public List<OrderDTOResponseStoreAndSupply> findAll(){
    return iStoreService.getAll();
    }

    @RequestMapping(path = "/orders/{id}/executed", method = RequestMethod.POST)
    public void executeById(@PathVariable Long id){
        iStoreService.executeOrder(id);

    }
}
