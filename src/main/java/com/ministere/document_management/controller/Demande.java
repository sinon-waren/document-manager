package com.ministere.document_management.controller;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.ministere.document_management.controller.enums.StatusDemande;
import com.ministere.document_management.controller.enums.TypeDemande;

@Entity
@Table(name = "demandes")
public class Demande {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeDemande typeDemande; 

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusDemande status;

    @Column(nullable = false)
    private LocalDate fillingDate; 

    protected Demande() {}

    public Demande(String name, String surname, TypeDemande typeDemande){
        this.name = name;
        this.surname = surname;
        this.typeDemande = typeDemande;
        this.status = StatusDemande.RECU; 
        this.fillingDate = LocalDate.now(); 
    }


    public String getName() { return name; }
    public String getSurname() { return surname; }
    public TypeDemande getTypeDemande() { return typeDemande; }
    public StatusDemande getStatus() { return status; }

    public void setStatus(StatusDemande status){
        this.status = status; 
    }



}
