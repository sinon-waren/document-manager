package com.ministere.document_management.repository;


import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.entity.Demande;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class DemandeRepositoryTest {
    @Autowired 
    private DemandeRepository demandeRepository;

    @Test
    void shouldSaveDemande(){
        Demande demande = new Demande(
            "Ali",
            "Benomar",
            TypeDemande.EMPLOI
        ); 

        Demande saved = demandeRepository.save(demande); 

        assertThat(saved.getId()).isNotNull(); 
    }
    
}
