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

    @Test
    public void shouldReturnDemandeDtoWhenIdExists() {
        
        Long id = 1L; 

        Demande demande = new Demande(); 
        demande.setId(id);
        demande.setName("John");

        when(demandeRepository.findById(id)).thenReturn(Optional.of(demande)); 

        DemandeResponseDto result = demandeService.recoverDemandeById(id); 

        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals("John", result.getName());

        verify(demandeRepository).findById(id); 
    }

    @Test
    public void shouldUpdateDemande() {

        Long id = 1L; 

        Demande demande = new Demande(); 
        demande.setId(id);
        demande.setStatus(StatusDemande.EN_COURS);

        DemandeRequestDto requestDto = new DemandeRequestDto(); 
        requestDto.setName("John Updated");
        requestDto.setSurname("Doe");
        requestDto.setTypeDemande(TypeDemande.BOURSE);

        when(demandeRepository.findById(id)).thenReturn(Optional.of(demande)); 
        when(demandeRepository.save(any(Demande.class))).thenReturn(demande); 

        DemandeResponseDto result = demandeService.updateDemande(id, requestDto); 

        assertEquals("John Updated", result.getName());
        assertEquals(StatusDemande.EN_COURS, result.getStatus());

        verify(demandeRepository).save(demande); 
    }





}
