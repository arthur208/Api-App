package br.edu.unicesumar.example.controller;

import br.edu.unicesumar.example.domain.Match;
import br.edu.unicesumar.example.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/matches")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    public ResponseEntity<Match> createMatch(@RequestBody Match match) {
        Match savedMatch = matchService.saveMatch(match);
        return ResponseEntity.ok(savedMatch);
    }

    @GetMapping
    public ResponseEntity<List<Match>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Match> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        return match != null ? ResponseEntity.ok(match) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.ok().build();
    }

    // Endpoints para iniciar e finalizar uma partida
    @PutMapping("/{id}/start")
    public ResponseEntity<Match> startMatch(@PathVariable Long id, @RequestParam Integer courtNumber) {
        Match match = matchService.startMatch(id, courtNumber);
        return match != null ? ResponseEntity.ok(match) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/finish")
    public ResponseEntity<Match> finishMatch(@PathVariable Long id, @RequestBody String result) {
        Match match = matchService.finishMatch(id, result);
        return match != null ? ResponseEntity.ok(match) : ResponseEntity.notFound().build();
    }
}
