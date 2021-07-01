package com.oga.produit.mapper.impl;

import com.oga.produit.entities.Category;
import com.oga.produit.mapper.CategoryMapper;
import com.oga.produit.mapper.ProduitMapper;
import com.oga.produit.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapperImpl implements CategoryMapper  {

    @Autowired
    private ProduitMapper produitMapper;



    @Override
    public CategoryModel toModel(Category entity) {
        if( entity == null )
        return null;

        return CategoryModel.builder()
                .idCategory(entity.getIdCategory())
                .dateCreation(entity.getDateCreation())
                .dateModification(entity.getDateModification())
                .nom(entity.getNom())
                .qte(entity.getQte())
                .produitModels(produitMapper.toListModel(entity.getProduits()))
                .build();

    }

    @Override
    public Category toEntity(CategoryModel model) {
        if( model == null ){
        return null;}

        return Category.builder()
                .idCategory(model.getIdCategory())
                .dateCreation(model.getDateCreation())
                .dateModification(model.getDateModification())
                .nom(model.getNom())
                .qte(model.getQte())
                .produits(produitMapper.toListEntity(model.getProduitModels()))
                .build();

    }

    @Override
    public List<Category> toListEntity(List<CategoryModel> listModel) {
        if( listModel == null ) {
            return null;
        }
        return listModel.stream().map(c->toEntity(c)).collect(Collectors.toCollection(() -> new ArrayList<Category>()));
    }

    @Override
    public List<CategoryModel> toListModel(List<Category> listEntity) {
        if( listEntity == null ) {
            return null;
        }
        return listEntity.stream().map(c->toModel(c)).collect(Collectors.toList());
    }
}
