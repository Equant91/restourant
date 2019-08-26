package com.equant.restourant.mapper;

import java.util.List;

public interface IMapperFacade {
    <S,D> void map(S sourceObject, D destinationObject);
    <S,D> D map(S sourceObject,Class<D>destinationObject);
    <S,D>List<D> mapList(Iterable<S> sourceObject, Class<D> destinationObject);
}
