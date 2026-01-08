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
/* 
@Bean
CommandLineRunner testService(DemandeService demandeService) {
    return args -> {
        Demande d = new Demande("Ali", "Youssef", TypeDemande.EMPLOI);
        demandeService.createDemande(d);

        demandeService.recoverAllDemande()
                .forEach(x -> System.out.println(x.getName()));
    };
}*/

}
