package ma.emsi.benazzouzwalid.tp1graphql.mapper;

import ma.emsi.benazzouzwalid.tp1graphql.dto.EtudiantDTO;
import ma.emsi.benazzouzwalid.tp1graphql.entity.Centre;
import ma.emsi.benazzouzwalid.tp1graphql.entity.Etudiant;
import ma.emsi.benazzouzwalid.tp1graphql.repository.CentreRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DtoToEtudiant {
    @Autowired
    private CentreRepository centreRepository;

    public void toEtudiant(Etudiant etudiant, EtudiantDTO dto) {
        if (dto != null) {
            Centre centre = centreRepository.findById(dto.centreId()).orElse(null);
            BeanUtils.copyProperties(dto, etudiant, "centreId");
            etudiant.setCentre(centre);
        }
    }
}
