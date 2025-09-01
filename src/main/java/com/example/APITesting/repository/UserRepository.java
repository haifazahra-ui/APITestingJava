package com.example.APITesting.repository;

import com.example.APITesting.entity.User; 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> { //crud repository 
    Optional<User> findByUsername(String username); //method buat nyari username di db
    Optional<User> findByEmail(String email); //method buat nyari email di db
    Boolean existsByUsername(String username); //method buat ngecek username di db ada atau engga (boolean true/false)
    Boolean existsByEmail(String email); //method buat ngecek email di db
}
