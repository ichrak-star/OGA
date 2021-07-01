package com.oga.produit.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import org.hibernate.annotations.CreationTimestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Table(name = "PRODUIT")

public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduit;
    @Column(name="NOM")
    private String nom;
    @Column(name="QUANTITE")
    private Double qte;
    @Column(name="DISPONIBILITE")
    private Boolean disponible;
    @CreationTimestamp
    @Column(name = "DATE_CREATION", nullable = false)
    private Timestamp dateCreation;
    @CreationTimestamp
    @Column(name = "DATE_MODIFICATION")
    private Timestamp dateModification;

    //@JsonIgnoreProperties(value="FK_CATEGORY", allowSetters=true,allowGetters = false)
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_CATEGORY",insertable = true,updatable = true)
    private Category category ;

}
