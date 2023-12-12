package br.edu.unicesumar.example.controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import br.edu.unicesumar.example.config.auth.jwt.Jwt;
import br.edu.unicesumar.example.domain.Company;
import br.edu.unicesumar.example.domain.UserManagement;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.CompanyService;
import br.edu.unicesumar.example.service.UserManagementService;
import lombok.SneakyThrows;

@RestController
@CrossOrigin("*") 
@RequestMapping("/usermanagement")
public class UserManagementController {
     @Autowired
    private UserManagementService userManagementService;

    // POST: Criar um novo UserManagement
    @PostMapping("/users/{userId}/companies/{companyId}")
    public ResponseEntity<UserManagement> createUserManagement(@PathVariable Long userId, @PathVariable Long companyId, @RequestBody UserManagement userManagement) {
        return new ResponseEntity<>(userManagementService.createUserManagement(userId, companyId, userManagement), HttpStatus.CREATED);
    }

    // GET: Obter um UserManagement por ID
    @GetMapping("/{id}")
    public ResponseEntity<UserManagement> getUserManagementById(@PathVariable Long id) {
        return ResponseEntity.ok(userManagementService.getUserManagementById(id));
    }

    // GET: Obter todos os UserManagements
    @GetMapping
    public List<UserManagement> getAllUserManagements() {
        return userManagementService.getAllUserManagements();
    }

    // PUT: Atualizar um UserManagement
    @PutMapping("/{id}")
    public ResponseEntity<UserManagement> updateUserManagement(@PathVariable Long id, @RequestBody UserManagement userManagement) {
        return ResponseEntity.ok(userManagementService.updateUserManagement(id, userManagement));
    }

    // DELETE: Excluir um UserManagement
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserManagement(@PathVariable Long id) {
        userManagementService.deleteUserManagement(id);
        return ResponseEntity.ok().build();
    }
}
