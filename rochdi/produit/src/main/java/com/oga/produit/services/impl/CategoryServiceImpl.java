package com.oga.produit.services.impl;

import com.oga.produit.entities.Category;
import com.oga.produit.exception.EntityNotFoundException;
import com.oga.produit.exception.ErrorCode;
import com.oga.produit.exception.InvalideEntityException;
import com.oga.produit.mapper.CategoryMapper;
import com.oga.produit.mapper.ProduitMapper;
import com.oga.produit.model.CategoryModel;
import com.oga.produit.repositories.CategoryRepository;
import com.oga.produit.repositories.ProduitRepository;
import com.oga.produit.services.CategoryService;
import com.oga.produit.validation.CategoryValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryValidation categoryValidation;
    @Autowired
    private ProduitMapper produitMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    private String erreurText ="Auccun category avec cet ID dans le BDD";


    @Override
    public Category create(CategoryModel entity) {
        List<String>errors=categoryValidation.categoryValidation(entity);
        if(!errors.isEmpty()){
            log.error("produit non valide");
            throw new InvalideEntityException("category non valid !!", ErrorCode.CATEGORY_NOT_VALID);
        }

        entity.setDateCreation(new Timestamp(System.currentTimeMillis()));
        entity.setDateModification(null);

        var category=categoryMapper.toEntity(entity);
        return categoryRepository.save(category);
    }


    @Override
    public Category update(Long id, CategoryModel entity)  {
        List<String>errors=categoryValidation.categoryValidation(entity);
        if(!errors.isEmpty()){
            log.error("produit non valide");
            throw new InvalideEntityException("category non valid !!", ErrorCode.CATEGORY_NOT_VALID);
        }

        if(!categoryRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(erreurText,ErrorCode.CATEGORY_NOT_FOUND);
        }
        if(id==null){
            log.error("ID null");
            throw new EntityNotFoundException("category n'existe pas avec l'ID="+id,ErrorCode.CATEGORY_NOT_FOUND);
        }
        entity.setDateCreation(entity.getDateCreation());
        entity.setDateModification(new Timestamp(System.currentTimeMillis()));

        var category=Category.builder()
                .idCategory(id)
                .produits(produitMapper.toListEntity(entity.getProduitModels()))
                .dateCreation(entity.getDateCreation())
                .dateModification(entity.getDateModification())
                .nom(entity.getNom())
                .qte(entity.getQte())
                .build();

        return categoryRepository.save(category);
    }
    @Override
    public boolean delete(Long id)  {
        if(!categoryRepository.findById(id).isPresent()){
            throw new EntityNotFoundException(erreurText,ErrorCode.CATEGORY_NOT_FOUND);
        }
        if(id==null){
            log.error("ID null");
            throw new EntityNotFoundException("category n'existe pas avec l'ID="+id,ErrorCode.CATEGORY_NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    public Category getOne(Long id) throws EntityNotFoundException {
        if(id==null){
            log.error("produit ID is null");
            return null;
        }
       return categoryRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(erreurText,ErrorCode.CATEGORY_NOT_FOUND));


    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> getAllPeage(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return new PageImpl<>(categoryPage.getContent(), pageable, categoryPage.getTotalElements());
    }


    @Override
    public Category getOneByNom(String nom) {
        return categoryRepository.findByNom(nom);
    }
}
