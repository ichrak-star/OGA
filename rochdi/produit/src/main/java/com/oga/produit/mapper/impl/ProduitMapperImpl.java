package com.oga.produit.mapper.impl;

import com.oga.produit.entities.Produit;
import com.oga.produit.mapper.CategoryMapper;
import com.oga.produit.mapper.ProduitMapper;
import com.oga.produit.model.ProduitModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProduitMapperImpl implements ProduitMapper {


    @Autowired
    private CategoryMapper categoryMapper;



    @Override
    public ProduitModel toModel(Produit entity) {
        if( entity == null )
        return null;

        return ProduitModel.builder()
                .idProduit(entity.getIdProduit())
                .disponible(entity.getDisponible())
                .dateCreation(entity.getDateCreation())
                .dateModification((entity.getDateModification()))
                .qte(entity.getQte())
                .nom(entity.getNom())
                .categoryModel(categoryMapper.toModel(entity.getCategory()))
                .build();

    }

    @Override
    public Produit toEntity(ProduitModel model) {
        if( model == null )
        return null;
        return Produit.builder()
                .idProduit(model.getIdProduit())
                .disponible(model.getDisponible())
                .dateCreation(model.getDateCreation())
                .dateModification(model.getDateModification())
                .qte(model.getQte())
                .nom(model.getNom())
                .category(categoryMapper.toEntity(model.getCategoryModel()))
                .build();

    }

    @Override
    public List<Produit> toListEntity(List<ProduitModel> listModel) {
        if( listModel == null )
        return null;

        return listModel.stream().map(p->toEntity(p)).collect(Collectors.toList());


    }

    @Override
    public List<ProduitModel> toListModel(List<Produit> listEntity) {
        if(listEntity==null)
        return null;
             return  listEntity.stream().map(p->toModel(p)).collect(Collectors.toList());
    }
}
