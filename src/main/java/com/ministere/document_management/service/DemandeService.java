package com.ministere.document_management.service;

import com.ministere.document_management.entity.*;

import java.util.List;

public interface DemandeService {

    Demande createDemande(Demande demande); 
    
    List <Demande> recoverAllDemande();

    Demande recoverDemandeById (Long id); 
    
}
