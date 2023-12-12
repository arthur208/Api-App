package br.edu.unicesumar.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.example.domain.UserManagement;

public interface UserManagementRepository extends JpaRepository<UserManagement, Long> {
    // Métodos adicionais se necessário
}
