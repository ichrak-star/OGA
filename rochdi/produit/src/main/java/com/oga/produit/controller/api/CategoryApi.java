package com.oga.produit.controller.api;

import com.oga.produit.entities.Category;
import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.model.CategoryModel;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
public interface CategoryApi {

    @PostMapping(path = "/add-category",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Category> create(@RequestBody CategoryModel entity);

    @PutMapping(path = "/update-category/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryModel entity) ;

    @DeleteMapping("/delete-category/{id}")
    ResponseEntity<Boolean> delete(@PathVariable Long id) ;

    @GetMapping("/{id}")
    public ResponseEntity<Category> getOne(@PathVariable Long id) throws EntityNotFoundException;

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll();

    @GetMapping("/nom/{nom}")
    public ResponseEntity<Category> getOneByNom(@PathVariable String nom);

    @GetMapping("/pagination")
    public ResponseEntity<Page<Category>> findAllPaginator(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "4") int size) ;

}


