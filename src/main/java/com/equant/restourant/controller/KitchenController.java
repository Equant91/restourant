package com.equant.restourant.controller;


import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.ProductDTOResponse;
import com.equant.restourant.service.kitchen.IKitchenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "/api/kitchen")
public class KitchenController {

    @Autowired
    IKitchenService iKitchenService;

    @RequestMapping(path = "/orders", method = RequestMethod.POST)
    public Long addOrder(@RequestBody OrderDTORequest orderDTORequest) {
        return iKitchenService.addOrder(orderDTORequest);
    }

    @RequestMapping(path = "/orders", method = RequestMethod.GET)
    public List<OrderDTOResponseKitchen> getAll() {
        return iKitchenService.getAll();
    }

    @RequestMapping(path = "/orders/{id}", method = RequestMethod.GET)
    public OrderDTOResponseKitchen getById(@PathVariable long id) {
        return iKitchenService.getById(id);
    }

    @RequestMapping(path = "/orders", method = RequestMethod.DELETE)
    public void remove(@RequestBody Long id)  {
       iKitchenService.remove(id);
    }

    @RequestMapping(path = "/products", method = RequestMethod.GET)
    public List<ProductDTOResponse> getAllProducts(){
       return iKitchenService.getAllProducts();
    }


}
