package com.ministere.document_management.controller;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.dto.DemandeResponseDto;
import com.ministere.document_management.entity.Demande;
import com.ministere.document_management.entity.enums.StatusDemande;
import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.service.DemandeService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(DemandeController.class)
public class DemandeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DemandeService demandeService;

    @Test
    void shouldCreateDemande() throws Exception {
        String requestJson = """
                {
                    "name": "John",
                    "surname": "Doe",
                    "typeDemande": "EMPLOI"
                }
                """;

        Demande demande = new Demande("John", "Doe", TypeDemande.EMPLOI); 
        demande.setId(1L);

        when(demandeService.createDemandeFromDto(any(DemandeRequestDto.class)))
            .thenReturn(demande); 

        mockMvc.perform(post("/api/demandes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.typeDemande").value("EMPLOI"))
                .andExpect(jsonPath("$.status").value("RECU")); 

    }
    
}
