package br.edu.unicesumar.example.dto.sign;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class SignUp {

    @NotEmpty
    private String FisrtName;
    @NotEmpty
    private String LastName;
    @NotEmpty
    private String DocumentNumber;
   
    @Email
    private String email;
    @NotEmpty
    private String Phone;
    @NotEmpty
    private String Birthday;
    @NotNull
    private int IsAgreeTermsConditions;
    @NotEmpty
    private String CreatedAt;

    @NotNull
    private String username;

    @Length(min = 6, max = 20)
    private String password;

}
