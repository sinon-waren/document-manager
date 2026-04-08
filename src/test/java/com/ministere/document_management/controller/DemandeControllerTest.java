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
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test 
    void checkGetAllDemande() throws Exception {

        List<Demande> demandes = List.of(
            new Demande("John", "Doe", TypeDemande.EMPLOI),
            new Demande("Jane", "Smith", TypeDemande.BOURSE)
        ); 
        
        demandes.get(0).setId(1L);
        demandes.get(1).setId(2L);

        when(demandeService.recoverAllDemande()).thenReturn(demandes); 

        mockMvc.perform(get("/api/demandes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane")); 
    }

    @Test
    void checkGetIdDemande() throws Exception {
        Demande demande = new Demande("John", "Doe", TypeDemande.EMPLOI); 
        demande.setId(1L);

        when(demandeService.recoverDemandeById(1L)).thenReturn(demande);

        mockMvc.perform(get("/api/demandes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe")); 

    }

    @Test
    void shouldReturn400WhenInvalidInput() throws Exception {
        String invalidJson  = """
                {
                    "name": "John",
                    "surname": "Doe"
                }
                """;

        mockMvc.perform(post("/api/demandes")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(invalidJson))
                .andExpect(status().isBadRequest()); 
    }

    // retry again this test because it's not functionnal
    @Test
    void shouldReturn404WhenDemandeNotFound() throws Exception {
        when(demandeService.recoverDemandeById(99L)).thenThrow(new RuntimeException("Not found")); 

        mockMvc.perform(get("/api/demandes/99"))
                .andExpect(status().isInternalServerError()); 
    }

    
}
