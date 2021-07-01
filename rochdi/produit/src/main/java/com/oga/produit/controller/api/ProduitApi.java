package com.oga.produit.controller.api;

import com.oga.produit.entities.Produit;
import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.model.ProduitModel;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
public interface ProduitApi {


    @PostMapping(path = "/add-produit",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Produit> create(@RequestBody ProduitModel entity);

    @PutMapping(path = "/update-produit/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Produit> update(@PathVariable Long id,@RequestBody ProduitModel entity) ;

    @DeleteMapping("/delete-produit/{id}")
    ResponseEntity delete(@PathVariable Long id) ;

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getOne(@PathVariable Long id) throws EntityNotFoundException;

    @GetMapping("")
    public ResponseEntity<List<Produit>> getAll();

    @GetMapping("/pagination")
    public ResponseEntity<Page<Produit>> findAllPaginator(@RequestParam(name = "page", defaultValue = "0") int page,
                                                    @RequestParam(name = "size", defaultValue = "4") int size) ;


    @PutMapping("/{idProduit}/add_category_to_produit/{idCategory}")
    public ResponseEntity<Produit> addCategoryToProduit(@PathVariable Long idProduit, @PathVariable  Long idCategory);

    @GetMapping("/categoryNom/{nom}")
    public ResponseEntity<List<Produit>>getAllByNomCategory(@PathVariable String  nom);

    @GetMapping("/category/{idCategory}")
    public ResponseEntity<List<Produit>> getAllByCategory(@PathVariable Long idCategory);

    @PostMapping(value = "/add-produit/{idCategory}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Produit> ajouterProduitParCategory(@RequestBody ProduitModel entity, @PathVariable Long idCategory);



}

