package com.ministere.document_management.service.impl;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.entity.*;
import com.ministere.document_management.repository.DemandeRepository;
import com.ministere.document_management.service.DemandeService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository; 
    
    public DemandeServiceImpl (DemandeRepository demandeRepository){
        this.demandeRepository = demandeRepository; 
    }

    @Override
    public Demande createDemande(Demande demande){
        return demandeRepository.save(demande); 
    }

    @Override
    public List<Demande> recoverAllDemande(){
        return demandeRepository.findAll(); 
    }

    @Override
    public Demande recoverDemandeById (Long id){
        return demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande not found")); 
    }

    @Override
    public Demande createDemandeFromDto(DemandeRequestDto dto){
        Demande demande = new Demande(dto.getName(), dto.getSurname(), dto.getTypeDemande()); 
        return demandeRepository.save(demande); 
    }

    
}
