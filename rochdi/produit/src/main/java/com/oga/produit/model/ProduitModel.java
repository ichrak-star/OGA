package com.oga.produit.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class ProduitModel {


    private Long idProduit;
    private String nom;
    private Double qte;
    private Boolean disponible;
    private Timestamp dateCreation;
    private Timestamp dateModification;

    private CategoryModel categoryModel ;

}
