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
public class UserManagementService {
    @Autowired
    private UserManagementRepository userManagementRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // Create: Adicionar um novo UserManagement
    public UserManagement createUserManagement(Long userId, Long companyId, UserManagement userManagement) {
        Users users = usersRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Users not found"));
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));

        userManagement.setUsers(users);
        userManagement.setCompany(company);
        return userManagementRepository.save(userManagement);
    }

    // Read: Obter um UserManagement por ID
    public UserManagement getUserManagementById(Long id) {
        return userManagementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserManagement not found"));
    }

    // Read: Obter todos os UserManagements
    public List<UserManagement> getAllUserManagements() {
        return userManagementRepository.findAll();
    }

    // Update: Atualizar um UserManagement existente
    public UserManagement updateUserManagement(Long id, UserManagement userManagementDetails) {
        UserManagement userManagement = userManagementRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("UserManagement not found"));

        // Atualizar atributos de UserManagement
        userManagement.setType(userManagementDetails.getType());
        userManagement.setIsActivated(userManagementDetails.getIsActivated());
        // Outros campos...

        return userManagementRepository.save(userManagement);
    }

    // Delete: Excluir um UserManagement
    public void deleteUserManagement(Long id) {
        userManagementRepository.deleteById(id);
    }
}
