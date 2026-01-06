package com.ministere.document_management;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.ministere.document_management.entity.Demande;
import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.repository.DemandeRepository;

@SpringBootApplication
public class DocumentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentManagementApplication.class, args);
	}

	@Bean
CommandLineRunner testDemande(DemandeRepository repository) {
    return args -> {
        repository.save(new Demande("Dupont", "Jean", TypeDemande.EMPLOI));
        repository.save(new Demande("Martin", "Sarah", TypeDemande.STAGE));

        repository.findAll().forEach(d ->
                System.out.println(d.getName() + " " + d.getStatus())
        );
    };
}

}
