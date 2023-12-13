package br.edu.unicesumar.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal basePrice;
    private Integer maxParticipants;
    private String specialInfo; // Informações sobre regras especiais

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_id")
    private Competition competition;

    // Atributos adicionais conforme discutido
    private boolean isDrawRequired; // Indica se é necessário sorteio para as duplas
    private String gameFormat; // Formato do jogo (pode ser uma enumeração ou string)
    private Integer ageLimit; // Limite de idade para a categoria, se aplicável
    private String gender; // Gênero (Masculino, Feminino, Misto)
    private String level; // Nível da competição (A, B, C, D)

    // Adicione outros campos conforme necessário
}
