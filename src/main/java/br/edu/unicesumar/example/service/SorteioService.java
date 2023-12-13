package br.edu.unicesumar.example.service;

import br.edu.unicesumar.example.domain.Registration;
import br.edu.unicesumar.example.repository.MatchRepository;
import br.edu.unicesumar.example.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class SorteioService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private MatchRepository matchRepository;

    public void sortearDuplas(Long categoryId) {
        List<Registration> registrations = registrationRepository.findByCategory_Id(categoryId);
        Collections.shuffle(registrations);

        // Implemente a lógica de formação de duplas e criação de partidas aqui
    }

    public void gerarPartidas(Long categoryId) {
        // Implemente a lógica para gerar partidas aqui
    }
}
