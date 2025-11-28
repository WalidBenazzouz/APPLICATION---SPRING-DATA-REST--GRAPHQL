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
    @Autowired
    private EtudiantRepository etRepo;
    @Autowired
    private CentreRepository ceRepo;
    @Autowired
    private ma.emsi.benazzouzwalid.tp1graphql.mapper.DtoToEtudiant mapper;

    @QueryMapping
    public List<Etudiant> listEtudiants() {
        return etRepo.findAll();
    }

    @QueryMapping
    public List<Centre> centres() {
        return ceRepo.findAll();
    }

    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id) {
        return etRepo.findById(id).orElse(null);
    }

    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant) {
        Etudiant e = new Etudiant();
        mapper.toEtudiant(e, etudiant);
        return etRepo.save(e);
    }

    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id, @Argument EtudiantDTO etudiant) {
        Etudiant e = etRepo.findById(id).orElse(null);
        if (e != null) {
            mapper.toEtudiant(e, etudiant);
            return etRepo.save(e);
        }
        return null;
    }

    @MutationMapping
    public Boolean deleteEtudiant(@Argument Long id) {
        if (etRepo.existsById(id)) {
            etRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
