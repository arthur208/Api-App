package br.edu.unicesumar.example.repository;

import br.edu.unicesumar.example.domain.Category;
import br.edu.unicesumar.example.domain.Competition;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
     List<Category> findByCompetition(Competition competition);
    // Adicione consultas personalizadas, se necessário
}
