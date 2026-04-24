package com.ministere.document_management.service;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.dto.DemandeResponseDto;
import com.ministere.document_management.entity.*;

import java.util.List;

public interface DemandeService {

    //Demande createDemande(Demande demande); 

    DemandeResponseDto createDemandeFromDto(DemandeRequestDto dto); 
    
    List <DemandeResponseDto> recoverAllDemande();

    DemandeResponseDto recoverDemandeById (Long id);

    DemandeResponseDto updateDemande(Long id, DemandeRequestDto requestDto);

    void deleteDemande(Long id); 

    
}
