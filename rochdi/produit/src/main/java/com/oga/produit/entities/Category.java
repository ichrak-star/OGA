package com.oga.produit.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    @Column(name="NOM")
    private String nom;
    @Column(name="Quantite")
    private Double qte;
    @CreationTimestamp
    @Column(name = "DATE_CREATION")
    private Timestamp dateCreation;
    @CreationTimestamp
    @Column(name = "DATE_MODIFICATIOn")
    private Timestamp dateModification;

    @JsonIgnoreProperties(value={"category"}, allowSetters=true,allowGetters = false)
    @JsonIgnore
    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Produit> produits ;


}
