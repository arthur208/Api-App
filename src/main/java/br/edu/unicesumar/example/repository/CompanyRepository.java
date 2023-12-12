package br.edu.unicesumar.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.example.domain.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    // Métodos adicionais se necessário
}