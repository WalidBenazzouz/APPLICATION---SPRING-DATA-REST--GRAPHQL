package ma.emsi.benazzouzwalid.tp1graphql.dto;

import ma.emsi.benazzouzwalid.tp1graphql.enumtype.Genre;

public record EtudiantDTO(
        String nom,
        String prenom,
        Genre genre,
        Long centreId
) {}
