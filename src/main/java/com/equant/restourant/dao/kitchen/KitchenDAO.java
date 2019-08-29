package com.equant.restourant.dao.kitchen;

import com.equant.restourant.model.Order;
import com.equant.restourant.model.Product;
import com.equant.restourant.model.dto.OrderDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Retention;
import java.util.Date;
import java.util.List;

@Repository
public class KitchenDAO implements IKitchenDAO {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Order> findAll() {
        return mongoTemplate.findAll(Order.class);
    }

    @Override
    public Order insertOrder(OrderDTORequest orderDTORequest) {
        Query query = new Query(Criteria.where("name").is(orderDTORequest.getName()));
        Product product =  mongoOperations.findOne(query,Product.class);

       if(product == null){
           product = new Product();
           product.setName(orderDTORequest.getName());
       }
        Order order = new Order();
        order.setWeight(orderDTORequest.getWeight());
        order.setDate(new Date());
        order.setProduct(product);
        order.setUrgency(orderDTORequest.getUrgency());
        order.setExecuted(false);

        return mongoTemplate.insert(order);
    }

    @Override
    public Order findById(long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Product> findAllProducts() {
        return null;
    }
}
