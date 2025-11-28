package ma.emsi.benazzouzwalid.tp1graphql.service;

import ma.emsi.benazzouzwalid.tp1graphql.dto.EtudiantDTO;
import ma.emsi.benazzouzwalid.tp1graphql.entity.Etudiant;
import ma.emsi.benazzouzwalid.tp1graphql.mapper.DtoToEtudiant;
import ma.emsi.benazzouzwalid.tp1graphql.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Service
public class EtudiantService {
    @Autowired
    private DtoToEtudiant dtoToEtudiant;
    @Autowired
    private EtudiantRepository etudiantRepository;

    private final Sinks.Many<Etudiant> sink = Sinks.many().multicast().onBackpressureBuffer();
    private final Sinks.Many<String> sinkSuppression = Sinks.many().multicast().onBackpressureBuffer();

    public List<Etudiant> getStudents() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiant(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant addEtudiant(EtudiantDTO etudiantDTO) {
        Etudiant etudiant = new Etudiant();
        dtoToEtudiant.toEtudiant(etudiant, etudiantDTO);
        Etudiant savedEtudiant = etudiantRepository.save(etudiant);
        sink.tryEmitNext(savedEtudiant);
        return savedEtudiant;
    }

    public Etudiant updateEtudiant(Long id, EtudiantDTO etudiantDTO) {
        if (etudiantRepository.findById(id).isPresent()) {
            Etudiant etudiant = etudiantRepository.findById(id).get();
            dtoToEtudiant.toEtudiant(etudiant, etudiantDTO);
            return etudiantRepository.save(etudiant);
        }
        return null;
    }

    public String deleteEtudiant(Long id) {
        if (etudiantRepository.findById(id).isPresent()) {
            Etudiant et = etudiantRepository.findById(id).get();
            etudiantRepository.deleteById(id);
            String msg = String.format("L'étudiant %s %s vient de quitter l'école !", et.getNom(), et.getPrenom());
            sinkSuppression.tryEmitNext(msg);
            return String.format("L'étudiant %s est bien supprimé !", id);
        }
        return String.format("L'étudiant %s n'existe pas !", id);
    }

    public Flux<Etudiant> getEtudiantAdded() {
        return sink.asFlux();
    }

    public Flux<String> etudiantRemoved() {
        return sinkSuppression.asFlux();
    }
}
