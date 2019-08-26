package com.equant.restourant.controller;


import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.ProductDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KitchenControllerTest {
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test1AddOrder() {

       int x = 1;
        for (OrderDTORequest order : getOrderDtoRequestList()) {
            Wrapper<Integer> id = restTemplate.postForObject("http://localhost:8888/api/kitchen/add", order, Wrapper.class);
            System.out.println(id.getData());
            System.out.println(x);
            Assert.assertTrue(id.getData().equals(x++));

        }
    }

    @Test
    public void test2GetAll() {
        WrapperForOrderDTOResponse wrapper = restTemplate.getForObject("http://localhost:8888/api/kitchen/list", WrapperForOrderDTOResponse.class);
        List<OrderDTOResponseKitchen> resultOrders =  wrapper.getData();
        List<OrderDTOResponseKitchen> orders = getOrderDtoResponseList();
        for (int x = 1; x < resultOrders.size(); x++) {
            Assert.assertTrue(resultOrders.get(x).getName().equals(orders.get(x).getName()));

            Assert.assertTrue(resultOrders.get(x).getWeight().equals(orders.get(x).getWeight()));
        }

    }

    @Test
    public void test3GetAllProducts() {
        WrapperForProductDTOResponse wfp = restTemplate.getForObject("http://localhost:8888/api/kitchen/products", WrapperForProductDTOResponse.class);
        List<ProductDTOResponse> resultProducts = wfp.getData();
        Assert.assertArrayEquals(resultProducts.toArray(), getProductDTOResponse().toArray());
    }

    @Test
    public void test4Remove() {
        Wrapper<String> response = restTemplate.postForObject("http://localhost:8888/api/kitchen/remove", 1, Wrapper.class);
        Assert.assertTrue(response.getData() == null);

    }

    private List<OrderDTORequest> getOrderDtoRequestList() {
        List<OrderDTORequest> orders = new ArrayList<>();
        orders.add(new OrderDTORequest("Свинина", 1400, true));
        orders.add(new OrderDTORequest("Сахар", 6000, true));
        orders.add(new OrderDTORequest("Говядина", 2000, false));
        orders.add(new OrderDTORequest("Соль", 1000, false));
        return orders;
    }

    private List<OrderDTOResponseKitchen> getOrderDtoResponseList() {
        List<OrderDTOResponseKitchen> orders = new ArrayList<>();
        orders.add(new OrderDTOResponseKitchen(1L, 1L, "Свинина", true, false, new Date(), 1440));
        orders.add(new OrderDTOResponseKitchen(2L, 2L, "Сахар", true, false, new Date(), 6000));
        orders.add(new OrderDTOResponseKitchen(3L, 3L, "Говядина", false, true, new Date(), 2000));
        orders.add(new OrderDTOResponseKitchen(4L, 4L, "Соль", false, true, new Date(), 1000));
        return orders;
    }

    private List<ProductDTOResponse> getProductDTOResponse(){
        List<ProductDTOResponse> orders = new ArrayList<>();
        orders.add(new ProductDTOResponse(1L,"Свинина"));
        orders.add(new ProductDTOResponse(2L,"Сахар"));
        orders.add(new ProductDTOResponse(3L,"Говядина"));
        orders.add(new ProductDTOResponse(4L,"Соль"));
        return orders;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class WrapperForOrderDTOResponse {
      private List<OrderDTOResponseKitchen> data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class WrapperForProductDTOResponse {
        private List<ProductDTOResponse> data;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Wrapper<T>{
        private T data;
    }
}