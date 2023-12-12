package br.edu.unicesumar.example.controller;

import org.springframework.data.domain.Pageable;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.edu.unicesumar.example.service.UsersService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.SneakyThrows;
import br.edu.unicesumar.example.domain.Users;

import java.net.URI;
import java.util.Optional;

@RestController
@CrossOrigin("*") 
@RequestMapping("/users")
public class UsersController {
    
    @Autowired
    private UsersService service;

    @GetMapping("/{id}")
    public ResponseEntity<Users> buscarPorId(@PathVariable(name="id") Long id) {
        Optional<Users> ProdutosOpt = this.service.findById(id);

        if(ProdutosOpt.isPresent()) {
            return ResponseEntity.ok(ProdutosOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<Users> salvar(@RequestBody Users Users) {
        Users UsersSalvo = service.save(Users);
        return ResponseEntity.created(new URI("/Users/" + Users.getId())).body(UsersSalvo);
    }

    @GetMapping
    public ResponseEntity<Page<Users>> buscarTodosPorNome(@RequestParam(name = "username", required = false, defaultValue = "") String username, Pageable pageable) {
        return ResponseEntity.ok(service.findAll(username, pageable));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Users> atualizar(@PathVariable("id") Long id, @RequestBody Users users) {
        if (!id.equals(users.getId())) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(service.update(users));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/me")
    public ResponseEntity<Users> getMe(@AuthenticationPrincipal Users user) {
        return ResponseEntity.ok(user);
    }

}
