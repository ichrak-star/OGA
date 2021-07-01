package com.oga.produit.services;


import com.oga.produit.entities.Category;
import com.oga.produit.model.CategoryModel;


public interface CategoryService extends BaseService<Category, CategoryModel,Long> {

    public Category getOneByNom (String nom);

}
