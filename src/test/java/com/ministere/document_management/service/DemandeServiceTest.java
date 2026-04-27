package com.ministere.document_management.service;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.dto.DemandeResponseDto;
import com.ministere.document_management.entity.Demande;
import com.ministere.document_management.entity.enums.StatusDemande;
import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.repository.DemandeRepository;
import com.ministere.document_management.service.impl.DemandeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DemandeServiceTest {

    @Mock
    private DemandeRepository demandeRepository; 

    @InjectMocks
    private DemandeServiceImpl demandeService;

    @Test
    public void shouldCreateDemandeFromDto() {
        DemandeRequestDto requestDto = new DemandeRequestDto(); 
        requestDto.setName("John");
        requestDto.setSurname("Doe");
        requestDto.setTypeDemande(TypeDemande.BOURSE);

        Demande saved = new Demande(); 

        saved.setId(1L);
        saved.setName("John");
        saved.setSurname("Doe");
        saved.setTypeDemande(TypeDemande.BOURSE);
        saved.setStatus(StatusDemande.EN_COURS);
        saved.setFillingDate(LocalDate.now());

        when(demandeRepository.save(any(Demande.class))).thenReturn(saved); 

        DemandeResponseDto result = demandeService.createDemandeFromDto(requestDto); 

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals(StatusDemande.EN_COURS, result.getStatus());
        verify(demandeRepository).save(any(Demande.class)); 

    }



}
