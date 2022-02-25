## Java Faker
#### Pour insérer des données au démarrage de Spring :
- Vide manuellement les tables suivantes en base :
  - adresse_arrivee
  - adresse_depart
  - annonce_covoiturage
  - utilisateur
  - reservation_covoiturage
  - reservation_vehicule

- ajouter la ligne suivante à application.properties :
  - spring.profiles.active=insertion

##### Attention, si les constantes peuvent être modifiées, NB_VEHIC_SERV doit correspondre à la réalité en base.