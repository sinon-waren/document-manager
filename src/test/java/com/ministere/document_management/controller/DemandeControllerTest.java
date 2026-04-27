package com.ministere.document_management.controller;

import com.ministere.document_management.dto.DemandeRequestDto;
import com.ministere.document_management.dto.DemandeResponseDto;
import com.ministere.document_management.entity.Demande;
import com.ministere.document_management.entity.enums.StatusDemande;
import com.ministere.document_management.entity.enums.TypeDemande;
import com.ministere.document_management.exception.DemandeNotFoundException;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
            "typeDemande": "EMPLOI",
            "status": "EN_COURS",
            "fillingDate": "2024-06-20"
        }
    """;

        DemandeResponseDto responseDto = new DemandeResponseDto(
            1L,
            "John",
            "Doe",
            TypeDemande.EMPLOI,
            StatusDemande.EN_COURS,
            LocalDate.of(2024, 6, 20)); 

        when(demandeService.createDemandeFromDto(any(DemandeRequestDto.class)))
            .thenReturn(responseDto); 

        mockMvc.perform(post("/api/demandes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.typeDemande").value("EMPLOI"))
                .andExpect(jsonPath("$.status").value("EN_COURS")); 

        verify(demandeService).createDemandeFromDto(any(DemandeRequestDto.class)); 

    }

    @Test 
    void checkGetAllDemande() throws Exception {

        DemandeResponseDto dto1 = new DemandeResponseDto(1L, "John", "Doe", TypeDemande.EMPLOI, StatusDemande.EN_COURS, LocalDate.now());
        DemandeResponseDto dto2 = new DemandeResponseDto(1L, "Jane", "Smith", TypeDemande.BOURSE, StatusDemande.RECU, LocalDate.now());

        when(demandeService.recoverAllDemande()).thenReturn(List.of(dto1, dto2)); 

        mockMvc.perform(get("/api/demandes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("John"))
                .andExpect(jsonPath("$[1].name").value("Jane"));

        verify(demandeService).recoverAllDemande(); 
    }

    @Test
    void checkGetIdDemande() throws Exception {
        Long id = 1L;
        DemandeResponseDto dto = new DemandeResponseDto(
                id,
                "John",
                "Doe",
                TypeDemande.EMPLOI,
                StatusDemande.EN_COURS,
                LocalDate.of(2024, 6, 20)); 
        // revoir le mapping de donner 
        when(demandeService.recoverDemandeById(id)).thenReturn(dto);


        mockMvc.perform(get("/api/demandes/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("John"))
                .andExpect(jsonPath("$.surname").value("Doe"))
                .andExpect(jsonPath("$.typeDemande").value("EMPLOI"))
                .andExpect(jsonPath("$.status").value("EN_COURS"));
        verify(demandeService).recoverDemandeById(id); 

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


    @Test
    void shouldReturn404WhenDemandeNotFound() throws Exception {
        Long id = 1L; 

        when(demandeService.recoverDemandeById(id)).thenThrow(new DemandeNotFoundException("Demande not found")); 

        mockMvc.perform(get("/api/demandes/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Demande not found"))
                .andExpect(jsonPath("$.status").value(404)); 
    }

    @Test
    void shouldDeleteDemandeWhenIdExists() throws Exception {
        Long id = 1L; 
        doNothing().when(demandeService).deleteDemande(id);

        mockMvc.perform(delete("/api/demandes/{id}", id))
                .andExpect(status().isNoContent()); 
        verify(demandeService).deleteDemande(id);
    }

    @Test
    void shouldReturn404WhenDeletingNoExistingDemande() throws Exception {
        Long id = 1L; 
        doThrow(new DemandeNotFoundException("Demande not found"))
                .when(demandeService).deleteDemande(id);
        
        mockMvc.perform(delete("/api/demandes/{id}", id))
                        .andExpect(status().isNotFound())
                        .andExpect(jsonPath("$.message").value("Demande not found"))
                        .andExpect(jsonPath("$.status").value(404)); 
        verify(demandeService).deleteDemande(id);
    }

    @Test
    void shouldUpdateDemandeWhenIdExists() throws Exception {

        Long id = 1L; 

        String requestJson = """
        {
            "name": "John Updated",
            "surname": "Doe",
            "typeDemande": "EMPLOI",
            "status": "EN_COURS",
            "fillingDate": "2024-06-20"
        }
        """;
        DemandeResponseDto dto = new DemandeResponseDto(
            id,
            "John Updated",
            "Doe",
            TypeDemande.EMPLOI,
            StatusDemande.EN_COURS,
            LocalDate.of(2024, 6, 20)); 

        when(demandeService.updateDemande(eq(id), any(DemandeRequestDto.class)))
                .thenReturn(dto); 
        
        mockMvc.perform(put("/api/demandes/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                            .andExpect(status().isOk())
                            .andExpect(jsonPath("$.id").value(id))
                            .andExpect(jsonPath("$.name").value("John Updated")); 
                    
        verify(demandeService).updateDemande(eq(id), any(DemandeRequestDto.class));


    }

    @Test
    void shouldReturn404WhenUpdatingNoExistingDemande() throws Exception {

        Long id = 1L; 

        String requestJson = """
        {
            "name": "John",
            "surname": "Doe",
            "typeDemande": "EMPLOI",
            "status": "EN_COURS",
            "fillingDate": "2024-06-20"
        }
        """;

        when(demandeService.updateDemande(eq(id), any(DemandeRequestDto.class)))
                .thenThrow(new DemandeNotFoundException("Demande not found")); 

        mockMvc.perform(put("/api/demandes/{id}", id)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                            .andExpect(status().isNotFound())
                            .andExpect(jsonPath("$.message").value("Demande not found"))
                            .andExpect(jsonPath("$.status").value(404));
        verify(demandeService).updateDemande(eq(id), any(DemandeRequestDto.class)); 
    }

    
}
