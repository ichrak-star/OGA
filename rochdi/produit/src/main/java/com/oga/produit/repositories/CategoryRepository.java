package com.oga.produit.repositories;

import com.oga.produit.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CategoryRepository extends JpaRepository <Category, Long> {

   public Category findByNom (String nom);
}
