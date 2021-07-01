package com.oga.produit.mapper;

import java.util.List;

public interface BaseMapper<E,M> {

    M toModel(E entity);
    E toEntity(M model);
    List<E> toListEntity(List<M> listModel);
    List<M> toListModel(List<E> listEntity);
}
