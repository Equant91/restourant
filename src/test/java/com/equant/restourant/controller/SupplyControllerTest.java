package com.equant.restourant.controller;

import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SupplyControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    List<Integer> ids = new ArrayList<>();

    @Before
    public  void init(){
        List<OrderDTORequest> orders = new ArrayList<>();
        orders.add(new OrderDTORequest("Свинина", 1400, true));
        orders.add(new OrderDTORequest("Сахар", 6000, true));
        orders.add(new OrderDTORequest("Говядина", 2000, false));
        orders.add(new OrderDTORequest("Соль", 1000, false));

        for (OrderDTORequest order : orders) {
            Wrapper<Integer> id =restTemplate.postForObject("http://localhost:8888/api/kitchen/add", order, Wrapper.class);
            ids.add(id.data);
        }
    }
    @After
    public void after(){
        for (Integer id : ids) {
        Wrapper<String> response = restTemplate.postForObject("http://localhost:8888/api/kitchen/remove", id, Wrapper.class);

        }
    }


    @Test
    public void test1executeById() {
       Wrapper<String> response = restTemplate.postForObject("http://localhost:8888/api/store/execute", ids.get(ids.size()- 2), Wrapper.class);
        Assert.assertTrue(response.getData()==null);
    }


    @Test
    public void test2FindAll() {
        WrapperForOrderDTOResponse wrapper = restTemplate.getForObject("http://localhost:8888/api/supply/list",WrapperForOrderDTOResponse.class);
        List<OrderDTOResponseStoreAndSupply> resultOrders = wrapper.getData();
        List<OrderDTOResponseStoreAndSupply> orders = getOrderDtoResponseList();
        for (int x = 1; x < orders.size(); x++) {
            System.out.println(resultOrders.get(x).getName() + orders.get(x).getName());
            Assert.assertTrue(resultOrders.get(x).getName().equals(orders.get(x).getName()));

            Assert.assertTrue(resultOrders.get(x).getWeight().equals(orders.get(x).getWeight()));
        }
    }
    private List<OrderDTOResponseStoreAndSupply> getOrderDtoResponseList() {
        List<OrderDTOResponseStoreAndSupply> orders = new ArrayList<>();
        orders.add(new OrderDTOResponseStoreAndSupply(3L, 3L, "Говядина", new Date(), 2000));
        orders.add(new OrderDTOResponseStoreAndSupply(4L, 4L, "Соль", new Date(), 1000));
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