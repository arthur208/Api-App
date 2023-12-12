package br.edu.unicesumar.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.unicesumar.example.domain.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
    // Métodos adicionais se necessário
}
