package br.edu.unicesumar.example.repository;

import br.edu.unicesumar.example.domain.Registration;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    List<Registration> findByCategory_Id(Long categoryId);
    // Adicione consultas personalizadas, se necessário
}
