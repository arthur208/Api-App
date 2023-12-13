package br.edu.unicesumar.example.service;

import br.edu.unicesumar.example.domain.Category;
import br.edu.unicesumar.example.domain.Competition;
import br.edu.unicesumar.example.repository.CategoryRepository;
import br.edu.unicesumar.example.repository.CompetitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Competition saveCompetition(Competition competition) {
        return competitionRepository.save(competition);
    }

    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public Competition getCompetitionById(Long id) {
        return competitionRepository.findById(id).orElse(null);
    }

    public void deleteCompetition(Long id) {
        competitionRepository.deleteById(id);
    }
    public List<Category> getCategoriesByCompetition(Long competitionId) {
        Competition competition = competitionRepository.findById(competitionId).orElse(null);
        return competition != null ? categoryRepository.findByCompetition(competition) : null;
    }
    // Aqui você pode adicionar outros métodos de serviço conforme necessário.
}
