package br.edu.unicesumar.example.service;

import br.edu.unicesumar.example.domain.Registration;
import br.edu.unicesumar.example.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public Registration saveRegistration(Registration registration) {
        return registrationRepository.save(registration);
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Registration getRegistrationById(Long id) {
        return registrationRepository.findById(id).orElse(null);
    }

    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }

    // Adicione outros métodos de serviço conforme necessário
}
