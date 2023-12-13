package br.edu.unicesumar.example.service;

import br.edu.unicesumar.example.domain.Match;
import br.edu.unicesumar.example.domain.MatchStatus;
import br.edu.unicesumar.example.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;

    public Match saveMatch(Match match) {
        return matchRepository.save(match);
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id).orElse(null);
    }

    public void deleteMatch(Long id) {
        matchRepository.deleteById(id);
    }

    // Métodos para iniciar e finalizar uma partida
    public Match startMatch(Long matchId, Integer courtNumber) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match != null) {
            match.setStatus(MatchStatus.IN_PROGRESS);
            match.setStartTime(LocalDateTime.now());
            match.setCourtNumber(courtNumber);
            return matchRepository.save(match);
        }
        return null;
    }

    public Match finishMatch(Long matchId, String result) {
        Match match = matchRepository.findById(matchId).orElse(null);
        if (match != null) {
            match.setStatus(MatchStatus.COMPLETED);
            match.setEndTime(LocalDateTime.now());
            match.setResult(result);
            return matchRepository.save(match);
        }
        return null;
    }
}
