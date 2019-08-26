package com.equant.restourant.dao.supply;

import com.equant.restourant.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SupplyDAO implements ISupplyDAO {
    EntityManager entityManager;

    @Autowired
    public SupplyDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Order> getAll() {
        System.out.println("============================");
        TypedQuery<Order> query = entityManager.createQuery("SELECT o FROM Order o WHERE o.urgency = 'false' AND o.executed = 'false' ", Order.class);
        List<Order> orders = query.getResultList();
        System.out.println(orders.toString());
        return  orders;
    }

    @Override
    public void executeOrder(Long id) {

    }
}
