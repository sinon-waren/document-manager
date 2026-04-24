package com.ministere.document_management.service;

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
import com.ministere.document_management.repository.DemandeRepository;
import com.ministere.document_management.service.impl.DemandeServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DemandeServiceTest {

    @Mock
    private DemandeRepository demandeRepository; 

    @InjectMocks
    private DemandeServiceImpl demandeService; 
/* 
    @Test 
    void shouldReturnDemandeWhenIdExists(){

        Long id = 1L; 

        Demande demande = new Demande();
        demande.setId(id);

        when(demandeRepository.findById(id)).thenReturn(Optional.of(demande));

        Demande result = demandeService.recoverDemandeById(id);

        assertNotNull(result);
        assertEquals(id, result.getId()); 

        verify(demandeRepository).findById(id); 

        
    }*/


}
