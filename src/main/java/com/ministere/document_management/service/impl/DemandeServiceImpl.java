package com.ministere.document_management.service.impl;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.entity.*;
import com.ministere.document_management.exception.DemandeNotFoundException;
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

   /*  @Override
    public Demande createDemande(Demande demande){
        return demandeRepository.save(demande); 
    }*/

    @Override
    public List<Demande> recoverAllDemande(){
        return demandeRepository.findAll(); 
    }

    @Override
    //Refactor controller, service, controller test with DTO
    public Demande recoverDemandeById (Long id){
        Demande demande = demandeRepository.findById(id)
            .orElseThrow(() -> new DemandeNotFoundException(
                    "Demande with ID " + id + " not found"
            ));
        return demande ; 
    }

    @Override
    public Demande createDemandeFromDto(DemandeRequestDto dto){
        Demande demande = new Demande(dto.getName(), dto.getSurname(), dto.getTypeDemande()); 
        return demandeRepository.save(demande); 
    }

    @Override
    public Demande updateDemande(Long id, Demande updateDemande){
        Demande existing = demandeRepository.findById(id)
                        .orElseThrow( () -> new DemandeNotFoundException(
                            "Demande with ID " + id + " not found"));
        existing.setName(updateDemande.getName());
        existing.setSurname(updateDemande.getSurname());
        existing.setTypeDemande(updateDemande.getTypeDemande());
        existing.setStatus(updateDemande.getStatus());
        
        return demandeRepository.save(existing); 

    }

    @Override
    public void deleteDemande(Long id){
        if(!demandeRepository.existsById(id)) {
            throw new DemandeNotFoundException("Demande with ID " + id + " not found"); 
        }

        demandeRepository.deleteById(id);
    }

    
}
