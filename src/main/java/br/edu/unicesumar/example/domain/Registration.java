package br.edu.unicesumar.example.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users; // Assumindo que há uma entidade User

    private LocalDateTime timestamp;
    private String instagramProfile;
    private String shirtSize;
    private Boolean contactVisibility;
    private Boolean partnerChangeAllowed;
    private Boolean isRegisteredVisible;

    // Adicione outros campos conforme necessário
}
