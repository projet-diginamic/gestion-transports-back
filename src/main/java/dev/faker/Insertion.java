package dev.faker;

import com.github.javafaker.Faker;
import dev.entites.*;
import dev.entites.adresse.Adresse;
import dev.entites.adresse.AdresseArrivee;
import dev.entites.adresse.AdresseDepart;
import dev.repositories.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.stream.IntStream;

//@Component
public class Insertion implements CommandLineRunner {

    @Autowired
    private CollaborateurRepository collaborateurRepository;

    @Autowired
    private ChauffeurRepository chauffeurRepository;

    @Autowired
    private AdresseDepartRepository repoAD;

    @Autowired
    private AdresseArriveeRepository repoAA;

    @Autowired
    private VehiculeServiceRepository vSRepo;

    @Autowired
    private VehiculeCovoiturageRepository vCRepo;

    @Autowired
    private AnnonceCovoiturageRepository aCRepo;

    @Autowired
    private ReservationCovoiturageRepository resaCRepo;

    @Autowired
    private ReservationVehiculeRepository resaVRepo;

    private int NB_CHAUFFEURS = 5;
    private int NB_COLLAB = 1000;
    private int NB_ADDR = 200;


    @Override
    public void run(String... args) throws Exception {
        Faker f = new Faker();
        Random r = new Random();

        /**
         * Création des chauffeurs
         */
        IntStream.range(0,this.NB_CHAUFFEURS-1).forEach(
                (x) -> {
                    Chauffeur c = new Chauffeur();
                    c.setNom(f.name().lastName());
                    c.setPrenom(f.name().firstName());
                    c.setEmail(c.getPrenom()+"."+c.getNom()+"@gestion-transports-diginamic.fr");
                    c.setPassword(f.internet().password());
                    this.chauffeurRepository.save(c);
                }
        );

        /**
         * Création des collaborateurs
         */
        IntStream.range(0,NB_COLLAB).forEach(
                (x) -> {
                    Collaborateur c = new Collaborateur();
                    c.setIsAdmin(false);
                    c.setNom(f.name().lastName());
                    c.setPrenom(f.name().firstName());
                    c.setEmail(c.getPrenom()+"."+c.getNom()+"@gestion-transports-diginamic.fr");
                    c.setPassword(f.internet().password());
                    this.collaborateurRepository.save(c);
                }
        );

        /**
         * Création des adresses départ
         */
        IntStream.range(0,NB_ADDR).forEach(
                (x) -> {
                    Adresse a = new Adresse();
                    a.setNumeroRue(f.address().streetAddressNumber());
                    a.setRue(f.address().streetAddress());
                    a.setCodePostal(f.address().zipCode());
                    a.setVille(f.address().cityName());
                    AdresseDepart ad = new AdresseDepart();
                    ad.setAdresse(a);
                    this.repoAD.saveAndFlush(ad);
                }
        );

        /**
         * Création des adresses arrivée
         */
        IntStream.range(0,NB_ADDR).forEach(
                (x) -> {
                    Adresse a = new Adresse();
                    a.setNumeroRue(f.address().streetAddressNumber());
                    a.setRue(f.address().streetName());
                    a.setCodePostal(f.address().zipCode());
                    a.setVille(f.address().cityName());
                    AdresseArrivee aa = new AdresseArrivee();
                    aa.setAdresse(a);
                    this.repoAA.save(aa);
                }
        );

    }


}
