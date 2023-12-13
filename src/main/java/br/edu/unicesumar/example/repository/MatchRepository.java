package br.edu.unicesumar.example.repository;

import br.edu.unicesumar.example.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    // Adicione consultas personalizadas, se necessário
}
