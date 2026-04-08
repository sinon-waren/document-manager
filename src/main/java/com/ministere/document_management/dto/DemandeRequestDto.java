package com.ministere.document_management.dto;

import com.ministere.document_management.entity.enums.TypeDemande;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

    

public class DemandeRequestDto {

    @NotBlank(message = "the name is required")
    private String name; 

    @NotBlank(message = "the surname is required")
    private String surname; 

    @NotNull
    private TypeDemande typeDemande; 

    public String getName(){
        return name; 
    }
    public void setName(String name){
        this.name = name; 
    }

    public String getSurname(){
        return surname; 
    }
    public void setSurname(String surname){
        this.surname = surname; 
    }

    public TypeDemande getTypeDemande(){
        return typeDemande; 
    }
    public void setTypeDemande(TypeDemande typeDemande){
        this.typeDemande = typeDemande; 
    }
    
    
}
