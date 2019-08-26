package com.equant.restourant.controller;


import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
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
public class StoreControllerTest {
    RestTemplate restTemplate = new RestTemplate();

    @BeforeClass
    public  void init(){

            List<OrderDTORequest> orders = new ArrayList<>();
            orders.add(new OrderDTORequest("Свинина", 1400, true));
            orders.add(new OrderDTORequest("Сахар", 6000, true));
            orders.add(new OrderDTORequest("Говядина", 2000, false));
            orders.add(new OrderDTORequest("Соль", 1000, false));

        for (OrderDTORequest order : orders) {
         restTemplate.postForObject("http://localhost:8888/api/kitchen/add", order, Wrapper.class);
        }
    }

    @Test
    public void test2ExecuteById() {
        Wrapper<String> response = restTemplate.postForObject("http://localhost:8888/api/store/execute", 2, Wrapper.class);

        Assert.assertTrue(response.data == null);
    }

    @Test
    public void test1FindAll() {
        WrapperForOrderDTOResponse wrapper = restTemplate.getForObject("http://localhost:8888/api/store/list", WrapperForOrderDTOResponse.class);
        List<OrderDTOResponseStoreAndSupply> resultOrders = wrapper.getData();
        List<OrderDTOResponseStoreAndSupply> orders = getOrderDtoResponseList();
        for (int x = 1; x < orders.size(); x++) {
            Assert.assertTrue(resultOrders.get(x).getName().equals(orders.get(x).getName()));

            Assert.assertTrue(resultOrders.get(x).getWeight().equals(orders.get(x).getWeight()));
        }
    }

    private List<OrderDTOResponseStoreAndSupply> getOrderDtoResponseList() {
        List<OrderDTOResponseStoreAndSupply> orders = new ArrayList<>();
        orders.add(new OrderDTOResponseStoreAndSupply(1L, 1L, "Свинина", new Date(), 1440));
        orders.add(new OrderDTOResponseStoreAndSupply(2L, 2L, "Сахар", new Date(), 6000));
        return orders;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class WrapperForOrderDTOResponse {
        private List<OrderDTOResponseStoreAndSupply> data;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class Wrapper<T>{
        private T data;
    }
}
