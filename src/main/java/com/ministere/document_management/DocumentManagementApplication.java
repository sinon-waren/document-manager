package com.ministere.document_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ministere.document_management.entity.Demande;
import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.repository.DemandeRepository;
import com.ministere.document_management.service.DemandeService;

@SpringBootApplication
public class DocumentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentManagementApplication.class, args);
	}

}
