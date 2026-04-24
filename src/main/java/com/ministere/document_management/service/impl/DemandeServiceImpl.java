package com.ministere.document_management.service.impl;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.dto.DemandeResponseDto;
import com.ministere.document_management.entity.*;
import com.ministere.document_management.entity.enums.StatusDemande;
import com.ministere.document_management.exception.DemandeNotFoundException;
import com.ministere.document_management.repository.DemandeRepository;
import com.ministere.document_management.service.DemandeService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandeServiceImpl implements DemandeService {

    private final DemandeRepository demandeRepository;

    private DemandeResponseDto mapToDto(Demande demande){
        return new DemandeResponseDto(
                    demande.getId(),
                    demande.getName(),
                    demande.getSurname(),
                    demande.getTypeDemande(),
                    demande.getStatus(),
                    demande.getFillingDate());
    }

    private Demande mapToEntity(DemandeRequestDto dto){
        Demande demande = new Demande();
        demande.setName(dto.getName());
        demande.setSurname(dto.getSurname());
        demande.setTypeDemande(dto.getTypeDemande());
        return demande; 

    }
    
    public DemandeServiceImpl (DemandeRepository demandeRepository){
        this.demandeRepository = demandeRepository; 
    }

   /*  @Override
    public Demande createDemande(Demande demande){
        return demandeRepository.save(demande); 
    }*/

    @Override
    public List<DemandeResponseDto> recoverAllDemande(){
        return demandeRepository.findAll()
                                .stream()
                                .map(this::mapToDto) 
                                .toList(); 
    }

    @Override
    //Refactor controller, service, controller test with DTO
    public DemandeResponseDto recoverDemandeById (Long id){
        Demande demande = demandeRepository.findById(id)
            .orElseThrow(() -> new DemandeNotFoundException(
                    "Demande with ID " + id + " not found"
            ));
        return mapToDto(demande) ; 
    }

    @Override
    public DemandeResponseDto createDemandeFromDto(DemandeRequestDto dto){
        Demande demande = new Demande(); 

        demande.setName(dto.getName());
        demande.setSurname(dto.getSurname());
        demande.setTypeDemande(dto.getTypeDemande());
        demande.setStatus(StatusDemande.EN_COURS);
        
        Demande saved = demandeRepository.save(demande); 

        return mapToDto(saved); 
    }

    @Override
    public DemandeResponseDto updateDemande(Long id, DemandeRequestDto requestDto){
        Demande existing = demandeRepository.findById(id)
                        .orElseThrow( () -> new DemandeNotFoundException(
                            "Demande with ID " + id + " not found"));
        existing.setName(requestDto.getName());
        existing.setSurname(requestDto.getSurname());
        existing.setTypeDemande(requestDto.getTypeDemande());

        Demande updated = demandeRepository.save(existing); 
        
        return mapToDto(updated); 

    }

    @Override
    public void deleteDemande(Long id){
        if(!demandeRepository.existsById(id)) {
            throw new DemandeNotFoundException("Demande with ID " + id + " not found"); 
        }

        demandeRepository.deleteById(id);
    }

    
}
