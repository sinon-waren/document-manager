package com.ministere.document_management.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ministere.document_management.repository.UserRepository;
import com.ministere.document_management.dto.LoginRequestDto;
import com.ministere.document_management.entity.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final UserRepository userRepository; 

    public AuthController(UserRepository userRepository){
        this.userRepository = userRepository; 
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto request){

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found")); 
        
        if(!user.getPassword().equals(request.getPassword())){
            throw new RuntimeException("Invalid password"); 
        }

        return ResponseEntity.ok("Login Successful"); 

    }
}
