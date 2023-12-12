package br.edu.unicesumar.example.service;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

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
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.repository.UsersRepository;

@Service
public class UsersService implements UserDetailsService {

    @Lazy
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTool jwtTokenTool;

    @Autowired
    private UsersRepository usersRepository;

    @Value("${escoladeti.auth.admin.username}")
    private String adminUsername;

    @Value("${escoladeti.auth.admin.password}")
    private String adminPassword;

    @Override
    public Users loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findUsersByUsername(username);
    }

    public Optional<Users> findById(Long id) {
		return this.usersRepository.findById(id);
	}

    public Jwt signIn(SignIn signIn) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(signIn.getUsername(), signIn.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Users userDetails = (Users) authentication.getPrincipal();

        return jwtTokenTool.generateToken(userDetails);

    }

    public Users signUp(SignUp signUp) {
        if (usersRepository.existsByUsername(signUp.getUsername())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username is already taken!");
        }

        if (usersRepository.existsByEmail(signUp.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email is already in use!");
        }

        Users users = Users.builder()
                .username(signUp.getUsername())
                .password(passwordEncoder.encode(signUp.getPassword()))
                .FisrtName(signUp.getFisrtName())
                .LastName(signUp.getLastName())
                .DocumentNumber(signUp.getDocumentNumber())
                .email(signUp.getEmail())
                .Phone(signUp.getPhone())
                .Birthday(signUp.getBirthday())
                .IsAgreeTermsConditions(signUp.getIsAgreeTermsConditions())
                .CreatedAt(signUp.getCreatedAt())
                .build();
                users.getRoles().add(Roles.ROLE_INTERNAL);

        return usersRepository.save(users);
    }

    @PostConstruct
    public void registerAdminUser() {

        if (!usersRepository.existsByUsername(this.adminUsername)) {
            Users admin = Users.builder()
                    .username(this.adminUsername)
                    .password(passwordEncoder.encode(this.adminPassword))
                    .FisrtName("Administrator")
                    .LastName("OF Motherfucker")
                    .DocumentNumber("1234567890")
                    .email("administrator@app.com")
                    .Phone("44991135840")
                    .Birthday("060623")
                    .IsAgreeTermsConditions(5)
                    .CreatedAt("12/12/23")
                    .build();

            admin.getRoles().add(Roles.ROLE_ADMIN);

            usersRepository.save(admin);
        }

    }

    public Users save(Users Users) {
        if (this.usersRepository.existsById(Users.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Matrícula já utilizada!");
        }
        return this.usersRepository.save(Users);
    }

    public Page<Users> findAll(String username, Pageable pageable) {
        return this.usersRepository.findByUsernameIgnoreCaseContaining(username, pageable);
    }


    public Users update(Users Users) {
        Users ProdutosBancoDeDados = this.usersRepository.findById(Users.getId()).orElse(null);
        if (ProdutosBancoDeDados == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        if (ProdutosBancoDeDados.getId().equals(ProdutosBancoDeDados.getId())) {
            return this.usersRepository.save(Users);
        }

        return this.save(Users);
    }

    public void delete(Long id) {
        if (!this.usersRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado!");
        }

        this.usersRepository.deleteById(id);
    }

}
