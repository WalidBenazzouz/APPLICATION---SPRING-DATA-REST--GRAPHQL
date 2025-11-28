package ma.emsi.benazzouzwalid.tp1graphql.service;

import ma.emsi.benazzouzwalid.tp1graphql.entity.Centre;
import ma.emsi.benazzouzwalid.tp1graphql.repository.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreService {
    @Autowired
    private CentreRepository centreRepository;

    public List<Centre> getCentres() {
        return centreRepository.findAll();
    }

    public Centre getCentre(Long id) {
        return centreRepository.findById(id).orElse(null);
    }
}
