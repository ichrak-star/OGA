package com.oga.produit.services.impl;

import com.oga.produit.entities.Category;
import com.oga.produit.model.CategoryModel;
import com.oga.produit.model.ProduitModel;
import com.oga.produit.services.CategoryService;
import com.oga.produit.services.ProduitService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest
class ProduitServiceImplTest {

    @Autowired
    private ProduitService produitService;
    @Autowired
    private CategoryService categoryService;

    @Test
    void create() {

        ProduitModel produitModel=ProduitModel.builder()
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .build();
        produitService.create(produitModel);
    }

    @Test
    void update() throws Exception {
        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .build();
        produitService.create(produitModel);
        ProduitModel produitModel1=produitModel;
        produitModel1.setNom("pro test update");
        produitService.update(produitModel.getIdProduit(),produitModel1);
    }

    @Test
    void delete() throws Exception {
        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .build();
        produitService.create(produitModel);
        produitService.delete(produitModel.getIdProduit());
    }

    @Test
    void getOne() {
        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .build();
        produitService.create(produitModel);
        produitService.getOne(produitModel.getIdProduit());
    }

    @Test
    void getAll() {
        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .build();
        produitService.create(produitModel);
        produitService.getAll();
    }

    @Test
    void getAllPeage() {
        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .build();
        produitService.create(produitModel);
        PageRequest pageRequest=PageRequest.of(0, 20);
        produitService.getAllPeage(pageRequest);
    }

    @Test
    void getAllByCategory() {

        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        categoryService.create(categoryModel);

        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .categoryModel(categoryModel)
                .build();
        produitService.create(produitModel);
        produitService.addCategoryToProduit(produitModel.getIdProduit(),categoryModel.getIdCategory());
        produitService.getAllByCategory(categoryModel.getIdCategory());

    }

    @Test
    void add_category_to_produit() {
        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        categoryService.create(categoryModel);

        ProduitModel produitModel=ProduitModel.builder()
                .idProduit(1L)
                .nom("pro Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .disponible(true)
                .qte(5.8)
                .categoryModel(categoryModel)
                .build();
        produitService.create(produitModel);
        produitService.addCategoryToProduit(produitModel.getIdProduit(),categoryModel.getIdCategory());
    }
}