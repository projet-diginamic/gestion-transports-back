CREATE TABLE adresse_depart (id INTEGER PRIMARY KEY, 
                        numero_rue VARCHAR(10),
                        rue VARCHAR (200),
                        code_postal VARCHAR (20),
                        ville VARCHAR (200) NOT NULL
                        );

CREATE TABLE adresse_arrivee(id INTEGER PRIMARY KEY,
                        numero_rue VARCHAR(10),
                        rue VARCHAR (200),
                        code_postal VARCHAR (20),
                        ville VARCHAR (200) NOT NULL
                        );

CREATE TABLE utilisateur(id INTEGER PRIMARY KEY, 
                           type VARCHAR (10) NOT NULL, 
                           is_admin BOOLEAN,
                           nom VARCHAR(200), 
                           prenom VARCHAR(200), 
                           email VARCHAR(200) NOT NULL, 
                           password VARCHAR(200)
                           );

CREATE TABLE vehicule_covoiturage(id INTEGER PRIMARY KEY,
                           immatriculation VARCHAR(10) NOT NULL,
                           marque VARCHAR(200),
                           modele VARCHAR(200)
                           );

CREATE TABLE annonce_covoiturage(id INTEGER PRIMARY KEY,
                         date_heure_depart DATETIME NOT NULL,
                         nb_places INTEGER NOT NULL,
                         adresse_depart INTEGER NOT NULL,
                         adresse_arrivee INTEGER NOT NULL,
                         id_vehicule INTEGER NOT NULL,
                         CONSTRAINT FK_covoiturage_depart FOREIGN KEY (adresse_depart) REFERENCES adresse_depart(id),
                         CONSTRAINT FK_covoiturage_arrivee FOREIGN KEY (adresse_arrivee) REFERENCES adresse_arrivee(id),
                         CONSTRAINT FK_covoiturage_vehicule FOREIGN KEY (id_vehicule) REFERENCES vehicule_covoiturage(id)
                         );

CREATE TABLE vehicule(id INTEGER PRIMARY KEY,
                      immatriculation VARCHAR (10) NOT NULL,
                      marque VARCHAR (200),
                      modele VARCHAR (200),
                      nb_places INTEGER NOT NULL,
                      statut VARCHAR (200),
                      categorie VARCHAR (200),
                      photo VARCHAR (200)
                      );

CREATE TABLE reservation_covoiturage( id INTEGER PRIMARY KEY,
				                        id_collaborateur INTEGER NOT NULL,
                                    id_covoiturage INTEGER NOT NULL,
                                    CONSTRAINT FK_covoiturage FOREIGN KEY (id_covoiturage) REFERENCES annonce_covoiturage(id),
				                        CONSTRAINT FK_covoiturage_collaborateur FOREIGN KEY (id_collaborateur) REFERENCES utilisateur(id)
                                    );

CREATE TABLE reservation_vehicule( id INTEGER PRIMARY KEY,
                                 	id_collaborateur INTEGER NOT NULL,
                                    id_vehicule INTEGER NOT NULL,
                                 	id_chauffeur INTEGER,
                                 	date_heure_depart DATETIME NOT NULL,
                                 	adresse_depart INTEGER NOT NULL,
                                 	adresse_arrivee INTEGER NOT NULL,
                                 	CONSTRAINT FK_vehicule_collaborateur FOREIGN KEY (id_collaborateur) REFERENCES utilisateur(id),
                              		CONSTRAINT FK_chauffeur FOREIGN KEY (id_chauffeur) REFERENCES utilisateur(id),
                                    CONSTRAINT FK_vehicule FOREIGN KEY (id_vehicule) REFERENCES vehicule(id),
                                 	CONSTRAINT FK_vehicule_depart FOREIGN KEY (adresse_depart) REFERENCES adresse_depart(id),
                                 	CONSTRAINT FK_vehicule_arrivee FOREIGN KEY (adresse_arrivee) REFERENCES adresse_arrivee(id)
                                    );