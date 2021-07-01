package com.oga.produit.validation;

import com.oga.produit.model.CategoryModel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
@Component
public class CategoryValidation {

    public List<String> categoryValidation(CategoryModel categoryModel){
        List<String> errors = new ArrayList<>();

        if(!StringUtils.hasLength(categoryModel.getNom())){
            errors.add("Veuillez saisir le Nom du CATEGORY");
        }
        if(categoryModel.getQte() == null){
            errors.add("Veuillez saisir la quantite du CATEGORY");
        }

        return errors;
    }
}
