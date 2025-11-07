# 🎓 Application Spring Data REST & GraphQL — Gestion des Étudiants et Centres

Ce projet est une application développée avec **Spring Boot**, intégrant :
- **JPA / Hibernate** pour la persistance,
- **Spring Data REST** pour l'exposition automatique de ressources REST,
- **Spring GraphQL** pour permettre des requêtes flexibles côté client,
- **H2 Database** pour l'environnement de développement et de démonstration.

L’objectif principal de ce TP est de comprendre :
- La modélisation des entités relationnelles,
- L’exposition des données via **GraphQL** (Queries & Mutations),
- L’utilisation de **DTO / Inputs GraphQL**,
- La configuration d’une base en mémoire H2,
- La manipulation des données via **GraphiQL UI**.

---

## 🏛️ Architecture du Projet

src/main/java/ma/emsi/benazzouzwalid/tp1graphql
│
├── entity
│ ├── Centre.java
│ └── Etudiant.java
│
├── enumtype
│ └── Genre.java
│
├── repository
│ ├── CentreRepository.java
│ └── EtudiantRepository.java
│
├── dto
│ └── EtudiantDTO.java
│
├── web
│ └── EtudiantGraphQLController.java
│
└── Tp1GraphQlApplication.java

yaml
Copy code

---

## 🧱 Modèle Conceptuel (MCD)

| Entité    | Attributs                       | Relations |
|-----------|--------------------------------|-----------|
| **Centre** | id, nom, adresse               | 1 → N avec Étudiant |
| **Étudiant** | id, nom, prénom, genre, centre | N → 1 avec Centre |

### 🔗 Relation :
- Un **centre** peut contenir **plusieurs étudiants**
- Chaque **étudiant** appartient à **un seul centre**

---

## 🧬 Enumération

```java
public enum Genre { Homme, Femme }
🗄️ Configuration H2 (in-memory)
ini
Copy code
spring.h2.console.enabled=true
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create
spring.graphql.graphiql.enabled=true
server.port=8090
Accès Console H2 :
bash
Copy code
http://localhost:8090/h2-console
🧪 Données Initiales (insertion automatique)
java
Copy code
@Bean
CommandLineRunner initDatabase(EtudiantRepository etRepo, CentreRepository ceRepo) {
    return args -> {
        Centre c1 = ceRepo.save(new Centre(null, "Maarif", "Biranzarane", null));
        Centre c2 = ceRepo.save(new Centre(null, "Oulfa", "Hay Hassani", null));

        etRepo.save(new Etudiant(null, "Adnani", "Brahim", Genre.Homme, c1));
        etRepo.save(new Etudiant(null, "Karimi", "Sara", Genre.Femme, c2));
    };
}
🧭 Interface GraphQL (GraphiQL UI)
Accéder à l’UI GraphQL :

bash
Copy code
http://localhost:8090/graphiql?path=/graphql
🔍 Exemple de Query (Lecture)
graphql
Copy code
query {
  listEtudiants {
    id
    nom
    prenom
    genre
    centre {
      nom
    }
  }
}
➕ Exemple de Mutation (Ajout)
graphql
Copy code
mutation {
  addEtudiant(etudiant: {
    nom: "Walid"
    prenom: "Benazzouz"
    genre: Homme
    centreId: 1
  }) {
    id
    nom
    prenom
    genre
    centre { nom }
  }
}
🔄 Exemple de Mutation (Mise à jour)
graphql
Copy code
mutation {
  updateEtudiant(id: 1, etudiant: {
    nom: "Walid"
    prenom: "Benn"
    genre: Homme
    centreId: 2
  }) {
    id
    nom
    prenom
    genre
    centre { nom }
  }
}
❌ Exemple de Mutation (Suppression)
graphql
Copy code
mutation {
  deleteEtudiant(id: 1)
}
🎯 Objectifs Pédagogiques Atteints
Compétence	Statut
Création d'entités JPA	✅
Relation OneToMany / ManyToOne	✅
Exposition REST automatique	✅
Mise en place de GraphQL	✅
Utilisation de Queries & Mutations	✅
Manipulation H2 + GraphiQL	✅

📝 Auteur
Walid Benazzouz
EMSI Casablanca – Génie Informatique
2024/2025

⭐ Recommandation
Si le projet t’a aidé → n’oublie pas de star ⭐ sur GitHub !

