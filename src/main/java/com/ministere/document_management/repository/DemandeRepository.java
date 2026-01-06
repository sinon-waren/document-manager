package com.ministere.document_management.repository;

import com.ministere.document_management.entity.*;
import com.ministere.document_management.entity.enums.StatusDemande;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List; 

public interface DemandeRepository extends JpaRepository <Demande, Long> {

    List<Demande> findByStatus ( StatusDemande status); 

   
}
