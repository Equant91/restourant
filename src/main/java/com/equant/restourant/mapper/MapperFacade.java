package com.equant.restourant.mapper;

import com.equant.restourant.model.Order;
import com.equant.restourant.model.dto.OrderDTOResponseKitchen;
import com.equant.restourant.model.dto.OrderDTOResponseStoreAndSupply;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapperFacade implements IMapperFacade {

    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacade(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
        mapperFactory.classMap(Order.class, OrderDTOResponseKitchen.class).field("id", "order_id").field("product.name", "name")
                .field("product.id", "product_id").byDefault().register();
        mapperFactory.classMap(Order.class, OrderDTOResponseStoreAndSupply.class).field("id", "order_id").field("product.name", "name")
                .field("product.id", "product_id").byDefault().register();
    }


    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject,destinationObject);
    }

    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject,destinationClass);
    }

    @Override
    public <S, D> List<D> mapList(Iterable<S> sourceObject, Class<D> destinationClass) {

        return mapperFactory.getMapperFacade().mapAsList(sourceObject,destinationClass);
    }
}
