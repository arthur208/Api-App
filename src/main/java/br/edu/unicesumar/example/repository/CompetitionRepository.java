package br.edu.unicesumar.example.repository;

import br.edu.unicesumar.example.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    // Aqui você pode adicionar métodos de consulta específicos, se necessário.
}
