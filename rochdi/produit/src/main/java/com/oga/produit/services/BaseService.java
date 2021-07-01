package com.oga.produit.services;

import com.oga.produit.exception.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface  BaseService<E,M, I> {
    E           create(M entity);
    E           update(I id, M entity) ;
    boolean     delete(I id)  ;
    E           getOne(I id) throws EntityNotFoundException;
    List<E>     getAll();
    Page<E> getAllPeage(Pageable pageable);

}