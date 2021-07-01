package com.oga.produit.repositories;

import com.oga.produit.entities.Category;
import com.oga.produit.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByCategory(Category category);
    List<Produit> findByNom(Category category);

}
