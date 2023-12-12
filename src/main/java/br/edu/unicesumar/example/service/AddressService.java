package br.edu.unicesumar.example.service;
import java.util.ArrayList;
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
import br.edu.unicesumar.example.domain.Address;
import br.edu.unicesumar.example.domain.Company;
import br.edu.unicesumar.example.domain.UserManagement;
import br.edu.unicesumar.example.domain.Users;
import br.edu.unicesumar.example.dto.sign.SignIn;
import br.edu.unicesumar.example.dto.sign.SignUp;
import br.edu.unicesumar.example.repository.AddressRepository;
import br.edu.unicesumar.example.repository.CompanyRepository;
import br.edu.unicesumar.example.repository.UserManagementRepository;
import br.edu.unicesumar.example.repository.UsersRepository;

@Service
public class AddressService {
   @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    // Create: Adicionar um novo endereço
    public Address createAddress(Long companyId, Address address) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        address.setCompany(company);
        return addressRepository.save(address);
    }

    // Read: Obter um endereço por ID
    public Address getAddressById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));
    }

    // Read: Obter todos os endereços
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    // Read: Obter endereços por uma empresa específica
    public List<Address> getAddressesByCompanyId(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        return new ArrayList<>(company.getAddresses());
    }

    // Update: Atualizar um endereço existente
    public Address updateAddress(Long companyId, Long addressId, Address addressDetails) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("Company not found"));
        Address address = addressRepository.findById(addressId)
                .orElseThrow(() -> new EntityNotFoundException("Address not found"));

        // Atualizar atributos do endereço
        address.setPostalCode(addressDetails.getPostalCode());
        address.setStreet(addressDetails.getStreet());
        address.setNumber(addressDetails.getNumber());
        address.setComplement(addressDetails.getComplement());
        address.setCity(addressDetails.getCity());
        address.setState(addressDetails.getState());
        address.setNeighborhood(addressDetails.getNeighborhood());
        address.setCompany(company);

        return addressRepository.save(address);
    }

    // Delete: Excluir um endereço
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}
