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
import br.edu.unicesumar.example.domain.Address;
import br.edu.unicesumar.example.domain.Company;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.service.AddressService;
import br.edu.unicesumar.example.service.CompanyService;
import lombok.SneakyThrows;

@RestController
@CrossOrigin("*") 
@RequestMapping("/address")
public class AddressController {
       @Autowired
    private AddressService addressService;

    // POST: Criar um novo endereço associado a uma empresa
    @PostMapping("/company/{companyId}")
    public ResponseEntity<Address> createAddress(@PathVariable Long companyId, @RequestBody Address address) {
        return new ResponseEntity<>(addressService.createAddress(companyId, address), HttpStatus.CREATED);
    }

    // PUT: Atualizar um endereço existente
    @PutMapping("/company/{companyId}/address/{addressId}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long companyId, @PathVariable Long addressId, @RequestBody Address addressDetails) {
        return ResponseEntity.ok(addressService.updateAddress(companyId, addressId, addressDetails));
    }

    // GET: Obter um endereço por seu ID
    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);
    }

    // GET: Obter todos os endereços
    @GetMapping
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }

    // DELETE: Excluir um endereço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        addressService.deleteAddress(id);
        return ResponseEntity.ok().build();
    }
}
