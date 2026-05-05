package com.ministere.document_management.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ministere.document_management.entity.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {

    Optional<User> findByUsername(String username); 
    
}
