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
        
        DemandeResponseDto response = demandeService.createDemandeFromDto(dto); 
        return new ResponseEntity<>(response, HttpStatus.CREATED); 
    }

   /*  @PostMapping
    public ResponseEntity<Demande> createDemande(@RequestBody Demande demande){
        Demande saved = demandeService.createDemande(demande); 
        return new ResponseEntity<>(saved, HttpStatus.CREATED); 
    }*/

    @GetMapping
    public ResponseEntity<List<DemandeResponseDto>> getAllDemandes() {
        return ResponseEntity.ok(demandeService.recoverAllDemande()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeResponseDto> getDemandeById(@PathVariable Long id){
        return ResponseEntity.ok(demandeService.recoverDemandeById(id)); 
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandeResponseDto> updateDemande (@PathVariable Long id, @RequestBody DemandeRequestDto dto){
        return ResponseEntity.ok(demandeService.updateDemande(id, dto)); 

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id){
        demandeService.deleteDemande(id);
        return ResponseEntity.noContent().build(); 
    }

    
}
