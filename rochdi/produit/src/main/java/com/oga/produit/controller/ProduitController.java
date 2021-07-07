package com.oga.produit.controller;


import com.oga.produit.controller.api.ProduitApi;
import com.oga.produit.entities.Produit;
import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.model.ProduitModel;
import com.oga.produit.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RequestMapping("/produit")
@CrossOrigin
@RestController
public class ProduitController implements ProduitApi {

    @Autowired
    private ProduitService produitService;

    @Override
    public ResponseEntity<Produit> create(ProduitModel entity) {
        entity.setDateCreation(new Timestamp(System.currentTimeMillis()));
        entity.setDateModification(null);
        return ResponseEntity.status(HttpStatus.OK).body(produitService.create(entity));
    }

    @Override
    public ResponseEntity<Produit> update(Long id, ProduitModel entity)  {
        entity.setDateCreation(entity.getDateCreation());
        entity.setDateModification(new Timestamp(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.OK).body(produitService.update(id, entity));
    }

    @Override
    public ResponseEntity delete(Long id)  {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.delete(id));
    }
    @Override
    public ResponseEntity<Produit> getOne(Long id) throws EntityNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.getOne(id));
    }


    @Override
    public ResponseEntity<List<Produit>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.getAll());
    }

    @Override
    public ResponseEntity<Page<Produit>> findAllPaginator(int page, int size) {
        Page<Produit> entitys = produitService.getAllPeage(PageRequest.of(page, size));
        return ResponseEntity.status(HttpStatus.OK).body(entitys);
    }


    @Override
    public ResponseEntity<Produit> addCategoryToProduit(Long idProduit, Long idCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.addCategoryToProduit(idProduit, idCategory));
    }

    @Override
    public ResponseEntity<List<Produit>> getAllByNomCategory(String nom) {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.getAllByNomCategory(nom));
    }

    @Override
    public ResponseEntity<List<Produit>> getAllByCategory(Long idCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.getAllByCategory(idCategory));
    }


    @Override
    public ResponseEntity<Produit> ajouterProduitParCategory(ProduitModel entity, Long idCategory) {
        return ResponseEntity.status(HttpStatus.OK).body(produitService.ajouterProduitParCategory(entity, idCategory));
    }




}
