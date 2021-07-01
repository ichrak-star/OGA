package com.oga.produit.validation;

import com.oga.produit.model.ProduitModel;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProduitValidation {

    public List<String> produitValidation(ProduitModel produitModel){
        java.util.List<java.lang.String> errors = new ArrayList<>();

        if(!StringUtils.hasLength(produitModel.getNom())){
            errors.add("Veuillez saisir le Nom du PRODUIT");
        }
        if(produitModel.getQte() == null){
            errors.add("Veuillez saisir le qantite du PRODUIT");
        }



        return errors;
    }
}
