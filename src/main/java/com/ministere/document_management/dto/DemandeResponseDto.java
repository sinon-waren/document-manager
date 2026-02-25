package com.ministere.document_management.dto;

import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.entity.enums.StatusDemande;

import java.time.LocalDate;


public class DemandeResponseDto {

    private Long id;
    private String name;
    private String surname;
    private TypeDemande typeDemande;
    private StatusDemande status;
    private LocalDate fillingDate;

    public DemandeResponseDto(
            Long id,
            String name,
            String surname,
            TypeDemande typeDemande,
            StatusDemande status,
            LocalDate fillingDate
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.typeDemande = typeDemande;
        this.status = status;
        this.fillingDate = fillingDate;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public TypeDemande getTypeDemande() { return typeDemande; }
    public StatusDemande getStatus() { return status; }
    public LocalDate getFillingDate() { return fillingDate; }

    
}
