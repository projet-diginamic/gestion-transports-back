package dev.faker;

import com.github.javafaker.Faker;
import dev.entites.AnnonceCovoiturage;
import dev.entites.Collaborateur;
import dev.entites.reservation.ReservationCovoiturage;
import dev.entites.reservation.ReservationVehicule;
import dev.repositories.*;
import dev.utils.Annonce;
import dev.utils.Resa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

//@Component
@Transactional
public class Insertion2 implements CommandLineRunner {

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
    private int NB_ANN = 100;
    private int NB_RESA = 100;
    private int NB_VEHIC_SERV = 5;

    @Override
    public void run(String... args) throws Exception {
        Faker f = new Faker();
        Random r = new Random();
        /**
         * Annonces covoiturage passées
         */
        IntStream.range(0,NB_ANN).forEach(
                (x) -> {
                    AnnonceCovoiturage ac = new AnnonceCovoiturage();
                    ac.setStatut(Annonce.ARCHIVE.getVal());
                    ac.setVehicule(this.vCRepo.findById(r.nextInt(5)+1).get());
                    ac.setNbPlaces(r.nextInt(5)+1);
                    ac.setOrganisateur(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    ac.setDateHeureDepart(LocalDateTime.ofInstant((f.date().past(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    ac.setAdresseDepart(this.repoAD.getById(r.nextInt(NB_ADDR-1)+1));
                    ac.setAdresseArrivee(this.repoAA.getById(r.nextInt(NB_ADDR-1)+1));
                    System.out.println("> "+ac);
//                    System.out.println(">>> "+ac.getAdresseDepart());
                    this.aCRepo.save(ac);
                }
        );

        /**
         * Annonces covoiturage avenir
         */
        IntStream.range(0,NB_ANN).forEach(
                (x) -> {
                    AnnonceCovoiturage ac = new AnnonceCovoiturage();
                    ac.setStatut(Annonce.OUVERT.getVal());
                    ac.setVehicule(this.vCRepo.findById(r.nextInt(5)+1).get());
                    ac.setNbPlaces(r.nextInt(5)+1);
                    ac.setOrganisateur(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    ac.setDateHeureDepart(LocalDateTime.ofInstant((f.date().future(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    ac.setAdresseDepart(this.repoAD.getById(r.nextInt(NB_ADDR-1)+1));
                    ac.setAdresseArrivee(this.repoAA.getById(r.nextInt(NB_ADDR-1)+1));
                    this.aCRepo.save(ac);
                }
        );

        /**
         * Resa covoiturage
         */
        IntStream.range(0,NB_RESA*2).forEach(
                (x) -> {
                    ReservationCovoiturage rc = new ReservationCovoiturage();
                    rc.setAnnonceCovoiturage(this.aCRepo.getById(r.nextInt(NB_ANN*2)+1));
                    if(rc.getAnnonceCovoiturage()
                            .getStatut()
                            .equals(Annonce.OUVERT.getVal())) rc.setStatut(Resa.ACTIF.getVal());
                    else rc.setStatut(Resa.ARCHIVE.getVal());
                    rc.setPassager(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    this.resaCRepo.save(rc);
                }
        );

        /**
         * Résa véhicule passées sans chauffeur
         */
        IntStream.range(0,NB_RESA).forEach(
                (x) -> {
                    ReservationVehicule rv = new ReservationVehicule();
                    rv.setStatut(Resa.ARCHIVE.getVal());
                    rv.setDemandeChauffeur(false);
                    rv.setDateHeureDepart(LocalDateTime.ofInstant((f.date().past(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    rv.setDateHeureRetour(rv.getDateHeureDepart().plusHours(6));
                    rv.setVehicule(this.vSRepo.getById(r.nextInt(NB_VEHIC_SERV)+1));
                    rv.setPassager(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    this.resaVRepo.save(rv);
                }
        );

        /**
         * Résa véhicule passées avec chauffeur
         */
        IntStream.range(0,NB_CHAUFFEURS-1).forEach(
                (x) -> {
                    ReservationVehicule rv = new ReservationVehicule();
                    rv.setStatut(Resa.ARCHIVE.getVal());
                    rv.setChauffeur(this.chauffeurRepository.getById(x+1));
                    rv.setDemandeChauffeur(false);
                    rv.setDateHeureDepart(LocalDateTime.ofInstant((f.date().past(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    rv.setDateHeureRetour(rv.getDateHeureDepart().plusHours(6));
                    rv.setVehicule(this.vSRepo.getById(r.nextInt(NB_VEHIC_SERV)+1));
                    rv.setPassager(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    this.resaVRepo.save(rv);
                }
        );

        /**
         * Résa véhicule futur avec chauffeur attribué
         */
        IntStream.range(0,NB_CHAUFFEURS-1).forEach(
                (x) -> {
                    ReservationVehicule rv = new ReservationVehicule();
                    rv.setStatut(Resa.ARCHIVE.getVal());
                    rv.setChauffeur(this.chauffeurRepository.getById(x+1));
                    rv.setDemandeChauffeur(true);
                    rv.setDateHeureDepart(LocalDateTime.ofInstant((f.date().future(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    rv.setDateHeureRetour(rv.getDateHeureDepart().plusHours(6));
                    rv.setVehicule(this.vSRepo.getById(r.nextInt(NB_VEHIC_SERV)+1));
                    rv.setPassager(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    this.resaVRepo.save(rv);
                }
        );

        /**
         * Résa véhicule futur avec chauffeur en attente
         */
        IntStream.range(0,NB_RESA).forEach(
                (x) -> {
                    ReservationVehicule rv = new ReservationVehicule();
                    rv.setStatut(Resa.ARCHIVE.getVal());
                    rv.setDemandeChauffeur(true);
                    rv.setDateHeureDepart(LocalDateTime.ofInstant((f.date().future(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    rv.setDateHeureRetour(rv.getDateHeureDepart().plusHours(6));
                    rv.setVehicule(this.vSRepo.getById(r.nextInt(NB_VEHIC_SERV)+1));
                    rv.setPassager(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    this.resaVRepo.save(rv);
                }
        );

        /**
         * Résa véhicule futur sans chauffeur
         */
        IntStream.range(0,NB_RESA).forEach(
                (x) -> {
                    ReservationVehicule rv = new ReservationVehicule();
                    rv.setStatut(Resa.ARCHIVE.getVal());
                    rv.setDemandeChauffeur(false);
                    rv.setDateHeureDepart(LocalDateTime.ofInstant((f.date().future(30, TimeUnit.DAYS)).toInstant(), ZoneId.of("UTC+1")));
                    rv.setDateHeureRetour(rv.getDateHeureDepart().plusHours(6));
                    rv.setVehicule(this.vSRepo.getById(r.nextInt(NB_VEHIC_SERV)+1));
                    rv.setPassager(this.collaborateurRepository.getById(r.nextInt(NB_COLLAB)+NB_CHAUFFEURS));
                    this.resaVRepo.save(rv);
                }
        );

        /**
         * Création de l'admin
         */
        Collaborateur ca = new Collaborateur();
        ca.setPrenom("Jean");
        ca.setNom("Dupond");
        ca.setEmail("admin@gestion-transports-diginamic.fr");
        ca.setPassword("admin");
        ca.setIsAdmin(true);
        this.collaborateurRepository.save(ca);
    }
}
