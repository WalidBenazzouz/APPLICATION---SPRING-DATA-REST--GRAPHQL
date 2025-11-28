package ma.emsi.benazzouzwalid.tp1graphql.web;

import ma.emsi.benazzouzwalid.tp1graphql.dto.EtudiantDTO;
import ma.emsi.benazzouzwalid.tp1graphql.entity.Centre;
import ma.emsi.benazzouzwalid.tp1graphql.entity.Etudiant;
import ma.emsi.benazzouzwalid.tp1graphql.service.CentreService;
import ma.emsi.benazzouzwalid.tp1graphql.service.EtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.util.List;

@Controller
public class EtudiantGraphQLController {
    @Autowired
    private EtudiantService etudiantService;
    @Autowired
    private CentreService centreService;

    @QueryMapping
    public List<Etudiant> getAllEtudiants() {
        return etudiantService.getStudents();
    }

    @QueryMapping
    public List<Centre> getAllCentres() {
        return centreService.getCentres();
    }

    @QueryMapping
    public Centre getCentreById(@Argument Long id) {
        return centreService.getCentre(id);
    }

    @QueryMapping
    public Etudiant getEtudiantById(@Argument Long id) {
        return etudiantService.getEtudiant(id);
    }

    @MutationMapping
    public Etudiant addEtudiant(@Argument EtudiantDTO etudiant) {
        return etudiantService.addEtudiant(etudiant);
    }

    @MutationMapping
    public Etudiant updateEtudiant(@Argument Long id, @Argument EtudiantDTO etudiant) {
        return etudiantService.updateEtudiant(id, etudiant);
    }

    @MutationMapping
    public String deleteEtudiant(@Argument Long id) {
        return etudiantService.deleteEtudiant(id);
    }

    @SubscriptionMapping
    public Flux<Etudiant> etudiantAdded() {
        return etudiantService.getEtudiantAdded();
    }

    @SubscriptionMapping
    public Flux<String> etudiantRemoved() {
        return etudiantService.etudiantRemoved();
    }
}
