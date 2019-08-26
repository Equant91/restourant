package com.equant.restourant.dao.kitchen;

import com.equant.restourant.model.Exception.NotFoundedException;
import com.equant.restourant.model.Order;
import com.equant.restourant.model.Product;
import com.equant.restourant.model.dto.OrderDTORequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public class KitchenDAO implements IKitchenDAO {

    EntityManager entityManager;

    @Autowired
    public KitchenDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    @Override
    public List<Order> getAll() {
        TypedQuery<Order> tq = entityManager.createQuery("SELECT o FROM Order o", Order.class);
        List<Order> list = tq.getResultList();
        return list;
    }

    @Transactional
    @Override
    public Order addOrder(OrderDTORequest orderDTORequest) {
        TypedQuery<Product> tq = entityManager.createQuery("SELECT o FROM Product o WHERE o.name = :name", Product.class);
        tq.setParameter("name", orderDTORequest.getName());
        Product product;
        try {
            product = tq.getSingleResult();
        } catch (NoResultException e) {
            product = new Product();
            product.setName(orderDTORequest.getName());

        }
        Order order = new Order();
        order.setWeight(orderDTORequest.getWeight());
        order.setDate(new Date());
        order.setProduct(product);
        order.setUrgency(orderDTORequest.getUrgency());
        order.setExecuted(false);
        entityManager.persist(order);

        return order;
    }

    @Transactional
    @Override
    public Order getById(long id) {
        return entityManager.find(Order.class, id);
    }

    @Transactional
    @Override
    public void remove(Long id)  {
        Order order = getById(id);
        if (order == null) {
            throw new NotFoundedException("Don't find this id");
        }
        entityManager.remove(order);

    }
    @Override
    public List<Product> getAllProducts() {
        TypedQuery<Product> tq = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> products = tq.getResultList();
        System.out.println(products);
        return tq.getResultList();
    }
}
