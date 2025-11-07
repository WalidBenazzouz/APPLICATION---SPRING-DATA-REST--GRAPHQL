package ma.emsi.benazzouzwalid.tp1graphql.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.emsi.benazzouzwalid.tp1graphql.enumtype.Genre;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Etudiant {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "centre_id")
    private Centre centre;
}
