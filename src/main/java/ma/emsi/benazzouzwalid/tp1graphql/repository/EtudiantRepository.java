package ma.emsi.benazzouzwalid.tp1graphql.repository;

import ma.emsi.benazzouzwalid.tp1graphql.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {}
