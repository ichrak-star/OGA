package com.oga.produit.services.impl;

import com.oga.produit.entities.Produit;
import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.exception.ErrorCode;
import com.oga.produit.exception.InvalideEntityException;
import com.oga.produit.mapper.CategoryMapper;
import com.oga.produit.mapper.ProduitMapper;
import com.oga.produit.model.ProduitModel;
import com.oga.produit.repositories.CategoryRepository;
import com.oga.produit.repositories.ProduitRepository;
import com.oga.produit.services.ProduitService;
import com.oga.produit.validation.ProduitValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
 public class ProduitServiceImpl implements ProduitService {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProduitValidation produitValidation;
    @Autowired
    private ProduitMapper produitMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    private String erreurText ="Auccun produit avec cet ID dans le BDD";
    private String produitInvalide="produit non valide";

    @Override
    public Produit create(ProduitModel entity) {

        List<String>errors=produitValidation.produitValidation(entity);
        if(!errors.isEmpty()){
            log.error(produitInvalide);
            throw new InvalideEntityException(produitInvalide, ErrorCode.PRODUIT_NOT_VALID);
        }

        entity.setDateCreation(new Timestamp(System.currentTimeMillis()));
        entity.setDateModification(null);
        var produit=produitMapper.toEntity(entity);
        return produitRepository.save(produit);
    }

    @Override
    public Produit update(Long id, ProduitModel entity)  {
        List<String>errors=produitValidation.produitValidation(entity);
        if(!errors.isEmpty()){
            log.error(produitInvalide);
            throw new InvalideEntityException(produitInvalide, ErrorCode.PRODUIT_NOT_VALID);
        }

        if(!produitRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(erreurText,ErrorCode.PRODUIT_NOT_FOUND);
        }
        if(id==null){
            log.error("ID null");
            throw new EntityNotFoundException(erreurText+id,ErrorCode.PRODUIT_NOT_FOUND);
        }
        entity.setDateCreation(entity.getDateCreation());
        entity.setDateModification(new Timestamp(System.currentTimeMillis()));
        var produit=Produit.builder()
                .idProduit(id)
                .qte(entity.getQte())
                .disponible(entity.getDisponible())
                .dateCreation(entity.getDateCreation())
                .dateModification(entity.getDateModification())
                .nom(entity.getNom())
                .category(categoryMapper.toEntity(entity.getCategoryModel()))
                .build();
        return produitRepository.save(produit);
    }

    @Override
    public boolean delete(Long id)  {
        if(!produitRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(erreurText,ErrorCode.PRODUIT_NOT_FOUND);
        }
        if(id==null){
            log.error("ID null");
            throw new EntityNotFoundException(erreurText+id,ErrorCode.PRODUIT_NOT_FOUND);
        }
        produitRepository.deleteById(id);
        return true;
    }

    @Override
    public Produit getOne(Long id) throws EntityNotFoundException {
        if(id==null){
            log.error("produit ID is null");
            return null;
        }
        return produitRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(erreurText,ErrorCode.PRODUIT_NOT_FOUND));


    }

    @Override
    public List<Produit> getAll() {
        return produitRepository.findAll();
    }

    @Override
    public Page<Produit> getAllPeage(Pageable pageable) {
        Page<Produit> produitPage = produitRepository.findAll(pageable);
        return new PageImpl<>(produitPage.getContent(), pageable, produitPage.getTotalElements());

    }



    @Override
    public List<Produit> getAllByCategory(Long idCategory) {
        return produitRepository.findByCategory(categoryRepository.getOne(idCategory));
    }

    @Override
    public List<Produit> getAllByNomCategory(String  nom) {
        return produitRepository.findByNom(categoryRepository.findByNom(nom));
    }


    @Transactional
    @Override
    public Produit addCategoryToProduit(Long idProduit, Long idCategory) {


        var category1=categoryRepository.getOne(idCategory);
        var produit1=produitRepository.getOne(idProduit);
        produit1.setCategory(category1);
        return produit1;

    }

    @Transactional
    @Override
    public Produit ajouterProduitParCategory(ProduitModel entity,Long idCategory) {

        List<String>errors=produitValidation.produitValidation(entity);
        if(!errors.isEmpty()){
            log.error(produitInvalide);
            throw new InvalideEntityException(produitInvalide, ErrorCode.PRODUIT_NOT_VALID);
        }

        entity.setDateCreation(new Timestamp(System.currentTimeMillis()));
        entity.setDateModification(null);

        var produit=produitMapper.toEntity(entity);

        produit.setCategory(categoryRepository.getOne(idCategory));

        return produitRepository.save(produit);
    }
}
