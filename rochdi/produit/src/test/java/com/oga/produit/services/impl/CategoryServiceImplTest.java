package com.oga.produit.services.impl;

import com.oga.produit.entities.Category;
import com.oga.produit.model.CategoryModel;
import com.oga.produit.services.CategoryService;
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
class CategoryServiceImplTest {
    @Autowired
    private CategoryService categoryService;

    @Test
    void create() {
        CategoryModel categoryModel = CategoryModel.builder()
                .nom("Cat Test")
                .dateCreation(new Timestamp(System.currentTimeMillis()))
                .dateModification(null)
                .qte(5.8)
                .build();
        Category category = categoryService.create(categoryModel);

    }


    @Test
    void update() throws Exception {
        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category2 = categoryService.create(categoryModel);

        CategoryModel categoryModel1=categoryModel;

        categoryModel1.setNom("cas test update");
        Category category = categoryService.update(categoryModel.getIdCategory(),categoryModel1);
    }

    @Test
    void delete() throws Exception {
        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category2 = categoryService.create(categoryModel);
        categoryService.delete(category2.getIdCategory());
    }

    @Test
    void getOne() {
        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category2 = categoryService.create(categoryModel);
        categoryService.getOne(category2.getIdCategory());
    }

    @Test
    void getAll() {
        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category = categoryService.create(categoryModel);

        CategoryModel categoryModel2=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category2 = categoryService.create(categoryModel2);
        categoryService.getAll();
    }

    @Test
    void getAllPeage() {

        CategoryModel categoryModel=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category = categoryService.create(categoryModel);

        CategoryModel categoryModel2=CategoryModel.builder()
                .idCategory(1L)
                .nom("Cat Test create")
                .dateCreation(null)
                .dateModification(new Timestamp(System.currentTimeMillis()))
                .qte(5.8)
                .build();
        Category category2 = categoryService.create(categoryModel2);
        PageRequest pageRequest=PageRequest.of(0, 20);
        categoryService.getAllPeage(pageRequest);
    }
}