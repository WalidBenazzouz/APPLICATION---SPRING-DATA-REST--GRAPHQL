package ma.emsi.benazzouzwalid.tp1graphql.web;

import ma.emsi.benazzouzwalid.tp1graphql.entity.*;
import ma.emsi.benazzouzwalid.tp1graphql.dto.EtudiantDTO;
import ma.emsi.benazzouzwalid.tp1graphql.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.*;
import org.springframework.stereotype.Controller;
import java.util.List;

@Controller
public class EtudiantGraphQLController {
    @Autowired private EtudiantRepository etRepo;
    @Autowired private CentreRepository ceRepo;

    @QueryMapping
    public List<Etudiant> listEtudiants(){ return etRepo.findAll(); }

    @QueryMapping
    public List<Centre> centres(){ return ceRepo.findAll(); }

    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO dto){
        Centre c = ceRepo.findById(dto.centreId()).orElse(null);
        Etudiant e = Etudiant.builder()
                .nom(dto.nom())
                .prenom(dto.prenom())
                .genre(dto.genre())
                .centre(c)
                .build();
        return etRepo.save(e);
    }
}
