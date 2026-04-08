package com.ministere.document_management.controller;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.dto.DemandeResponseDto;
import com.ministere.document_management.entity.Demande;
import com.ministere.document_management.service.DemandeService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandes")
public class DemandeController {

    private final DemandeService demandeService; 

    public DemandeController (DemandeService demandeService){
        this.demandeService = demandeService; 
    }

    @PostMapping
    public ResponseEntity<DemandeResponseDto> createDemandeFromDto( @Valid @RequestBody DemandeRequestDto dto){
        Demande demande = demandeService.createDemandeFromDto(dto); 

        DemandeResponseDto response = new DemandeResponseDto(
            demande.getId(),
            demande.getName(),
            demande.getSurname(),
            demande.getTypeDemande(),
            demande.getStatus(),
            demande.getFillingDate()); 
        return new ResponseEntity<>(response, HttpStatus.CREATED); 
    }

   /*  @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande){
        Demande saved = demandeService.createDemande(demande); 
        return new ResponseEntity<>(saved, HttpStatus.CREATED); 
    }*/

    @GetMapping
    public ResponseEntity<List<Demande>> getAllDemandes() {
        return ResponseEntity.ok(demandeService.recoverAllDemande()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Demande> getDemandeById(@PathVariable Long id){
        return ResponseEntity.ok(demandeService.recoverDemandeById(id)); 
    }

    
}
