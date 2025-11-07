package ma.emsi.benazzouzwalid.tp1graphql;

import ma.emsi.benazzouzwalid.tp1graphql.entity.Centre;
import ma.emsi.benazzouzwalid.tp1graphql.entity.Etudiant;
import ma.emsi.benazzouzwalid.tp1graphql.enumtype.Genre;
import ma.emsi.benazzouzwalid.tp1graphql.repository.CentreRepository;
import ma.emsi.benazzouzwalid.tp1graphql.repository.EtudiantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Tp1GraphQlApplication {

    public static void main(String[] args) {
        SpringApplication.run(Tp1GraphQlApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(EtudiantRepository etudiantRepository, CentreRepository centreRepository){
        return args -> {

            // Création de centres
            Centre c1 = centreRepository.save(
                    Centre.builder().nom("Maarif").adresse("Biranzarane").build()
            );
            Centre c2 = centreRepository.save(
                    Centre.builder().nom("Oulfa").adresse("Hay Hassani").build()
            );

            // Création d'étudiants liés à des centres
            etudiantRepository.save(
                    Etudiant.builder().nom("Adnani").prenom("Brahim").genre(Genre.Homme).centre(c1).build()
            );
            etudiantRepository.save(
                    Etudiant.builder().nom("Karimi").prenom("Sara").genre(Genre.Femme).centre(c2).build()
            );
        };
    }
}
