package com.oga.produit.services;


import com.oga.produit.entities.Produit;
import com.oga.produit.model.ProduitModel;


import java.util.List;

public interface ProduitService extends BaseService<Produit, ProduitModel,Long> {

    public List<Produit> getAllByCategory(Long idCategory);
    public List<Produit> getAllByNomCategory(String  nom);

    public Produit addCategoryToProduit(Long idProduit,Long idCategory);

    public Produit ajouterProduitParCategory(ProduitModel entity,Long idCategory);
}
