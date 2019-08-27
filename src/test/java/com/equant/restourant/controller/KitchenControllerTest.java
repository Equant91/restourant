package com.equant.restourant.controller;


import com.equant.restourant.model.dto.OrderDTORequest;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.ProductDTOResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.JSONUtil;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KitchenControllerTest {

    RestTemplate restTemplate = new RestTemplate();

    List<Integer> ids = new ArrayList<>();

    @LocalServerPort
    private int port;

    @Test
    public void test1AddOrder() {


        for (OrderDTORequest order : getOrderDtoRequestList()) {
            Wrapper<Integer> id = restTemplate.postForObject("http://localhost:" + port +"/api/kitchen/orders", order, Wrapper.class);
            System.out.println(id.data);
             ids.add(id.data);
            Assert.assertTrue(id.data>0);
        }
    }
    @Test
    public void test2GetAll() {
        WrapperForOrderDTOResponse wrapper = restTemplate.getForObject("http://localhost:" + port +"/api/kitchen/orders", WrapperForOrderDTOResponse.class);
        List<OrderDTOResponseKitchen> resultOrders =  wrapper.getData();
        List<OrderDTOResponseKitchen> orders = getOrderDtoResponseList();
        for (int x = 1; x < ids.size(); x++) {
            Assert.assertTrue(resultOrders.get(x).getName().equals(orders.get(x).getName()));

            Assert.assertTrue(resultOrders.get(x).getWeight().equals(orders.get(x).getWeight()));
        }

    }

    @Test
    public void test3GetAllProducts() {
        WrapperForProductDTOResponse wfp = restTemplate.getForObject("http://localhost:" + port +"/api/kitchen/products", WrapperForProductDTOResponse.class);
        List<ProductDTOResponse> resultProducts = wfp.getData();
        Assert.assertArrayEquals(resultProducts.toArray(), getProductDTOResponse().toArray());
    }

    @Test
    public void test4Remove() {
        for(Integer id: ids) {
             restTemplate.delete("http://localhost:" + port +"/api/kitchen/orders");

        }
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
