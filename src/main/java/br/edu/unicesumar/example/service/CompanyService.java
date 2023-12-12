package br.edu.unicesumar.example.service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.edu.unicesumar.example.config.auth.Roles;
import br.edu.unicesumar.example.config.auth.jwt.Jwt;
import br.edu.unicesumar.example.config.auth.jwt.JwtTool;
import br.edu.unicesumar.example.domain.Company;
import br.edu.unicesumar.example.domain.UserManagement;
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.repository.CompanyRepository;
import br.edu.unicesumar.example.repository.UserManagementRepository;
import br.edu.unicesumar.example.repository.UsersRepository;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    // Create
    public Company createCompany(Company company) {
        // Aqui você pode adicionar lógica antes de salvar, como validações
        return companyRepository.save(company);
    }

    // Read
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    // Update
    public Company updateCompany(Long id, Company companyDetails) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        // Atualizar atributos da empresa
        company.setRazaoSocial(companyDetails.getRazaoSocial());
        company.setCNPJ(companyDetails.getCNPJ());
        company.setEmail(companyDetails.getEmail());
        company.setPhone(companyDetails.getPhone());
        // Outros campos...

        return companyRepository.save(company);
    }

    // Delete
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);
    }
}
