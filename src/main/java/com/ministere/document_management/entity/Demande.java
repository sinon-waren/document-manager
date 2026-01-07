package com.ministere.document_management.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.ministere.document_management.entity.enums.StatusDemande;
import com.ministere.document_management.entity.enums.TypeDemande;

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

    public LocalDate getFillinDate; 

    protected Demande() {}

    public Demande(String name, String surname, TypeDemande typeDemande){
        this.name = name;
        this.surname = surname;
        this.typeDemande = typeDemande;
        this.status = StatusDemande.RECU; 
        this.fillingDate = LocalDate.now(); 
    }

    public Long getId() {return id;}
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public TypeDemande getTypeDemande() { return typeDemande; }
    public StatusDemande getStatus() { return status; }
    public LocalDate getFillinDate() { return fillingDate; }

    public void setName(String name) {
        this.name = name; 
    }

    public void setSurnamer(String surname) {
        this.surname = surname; 
    }

    public void setTypeDemande(TypeDemande typeDemande){
        this.typeDemande = typeDemande; 
    }

    public void setStatus(StatusDemande status){
        this.status = status; 
    }




}
