package br.edu.unicesumar.example.controller;

import br.edu.unicesumar.example.domain.Category;
import br.edu.unicesumar.example.domain.Competition;
import br.edu.unicesumar.example.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/competitions")
public class CompetitionController {

    @Autowired
    private CompetitionService competitionService;

    @PostMapping
    public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) {
        Competition savedCompetition = competitionService.saveCompetition(competition);
        return ResponseEntity.ok(savedCompetition);
    }

    @GetMapping
    public ResponseEntity<List<Competition>> getAllCompetitions() {
        List<Competition> competitions = competitionService.getAllCompetitions();
        return ResponseEntity.ok(competitions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Competition> getCompetitionById(@PathVariable Long id) {
        Competition competition = competitionService.getCompetitionById(id);
        return competition != null ? ResponseEntity.ok(competition) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompetition(@PathVariable Long id) {
        competitionService.deleteCompetition(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("/{id}/categories")
    public ResponseEntity<List<Category>> getCategoriesByCompetition(@PathVariable Long id) {
        List<Category> categories = competitionService.getCategoriesByCompetition(id);
        return categories != null ? ResponseEntity.ok(categories) : ResponseEntity.notFound().build();
    }

    // Aqui você pode adicionar outros endpoints conforme necessário.
}
