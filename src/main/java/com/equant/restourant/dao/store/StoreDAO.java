package com.equant.restourant.dao.store;

import com.equant.restourant.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StoreDAO implements IStoreDAO {

    EntityManager entityManager;

    @Autowired
    public StoreDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public List<Order> getAll() {
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.urgency = 'true' AND o.executed = 'false' ", Order.class);
        List<Order> orders = query.getResultList();
        return  orders;
    }

    @Transactional
    @Override
    public void executeOrder(Long id){
       Order order =  entityManager.find(Order.class, id);
       if(order.getExecuted() == true){
           throw new RuntimeException("Order already executed.");
       }
       else{
           order.setExecuted(true);

       }
    }
}
