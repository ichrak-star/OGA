package com.oga.produit.controller;

import com.oga.produit.controller.api.CategoryApi;
import com.oga.produit.entities.Category;
import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.model.CategoryModel;
import com.oga.produit.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/category")
@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CategoryController implements CategoryApi {

    @Autowired
    private CategoryService  categoryService;

    @Override
    public ResponseEntity<Category> create(CategoryModel entity) {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.create(entity));
    }

    @Override
    public ResponseEntity<Category> update(Long id, CategoryModel entity) {

        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id,entity));
    }

    @Override
    public ResponseEntity<Boolean> delete(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.delete(id));

    }

    @Override
    public ResponseEntity<Category> getOne(Long id) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOne(id));

    }

    @Override
    public ResponseEntity<List<Category>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }

    @Override
    public ResponseEntity<Category> getOneByNom(String nom) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getOneByNom(nom));
    }

    @Override
    public ResponseEntity<Page<Category>> findAllPaginator(int page, int size) {
        Page<Category> entitys = categoryService.getAllPeage(PageRequest.of(page, size));
        return ResponseEntity.status(HttpStatus.OK).body(entitys);
    }
}
