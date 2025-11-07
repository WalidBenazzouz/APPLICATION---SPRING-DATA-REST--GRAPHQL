package ma.emsi.benazzouzwalid.tp1graphql.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Centre {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String adresse;

    @OneToMany(mappedBy = "centre")
    private List<Etudiant> listEtudiants;
}
