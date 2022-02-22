package dev.services;

import dev.dto.reservation.vehicule.AccepterMissionDto;
import dev.dto.reservation.vehicule.ReservationVehiculeDto;
import dev.entites.Chauffeur;
import dev.entites.reservation.ReservationVehicule;
import dev.exception.DateDepasseeException;
import dev.exception.NotFoundException;
import dev.repositories.*;
import dev.utils.Email;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service des réservations de véhicules de service
 */
@Service
public class ReservationVehiculeService {

    private ReservationVehiculeRepository repository;
    private CollaborateurRepository collaborateurRepository;
    private ChauffeurRepository chauffeurRepository;
    private VehiculeServiceRepository vRepo;
    private Email email;

    public ReservationVehiculeService(ReservationVehiculeRepository repository, CollaborateurRepository collaborateurRepository, ChauffeurRepository chauffeurRepository, VehiculeServiceRepository vRepo, Email email) {
        this.repository = repository;
        this.collaborateurRepository = collaborateurRepository;
        this.chauffeurRepository = chauffeurRepository;
        this.vRepo = vRepo;
        this.email = email;
    }

    /**
     * Renvoie la liste paginé des resas véhicules
     * @param pr
     * @return
     */
    public List<ReservationVehicule> listerVehicules(PageRequest pr) {
        return this.repository.findAll(pr).toList();
    }

    /**
     * Renvoie une résa véhicule par id
     * @param id
     * @return
     * @throws NotFoundException
     */
    public ReservationVehicule afficherResa(Integer id) throws NotFoundException {
        return this.repository.findById(id)
                .orElseThrow(NotFoundException::new);
    }

    /**
     * Lister toutes les résas d'un passager
     * @param id
     * @return
     */
    public List<ReservationVehicule> listerMesResas(Integer id){
        return this.repository.findByPassagerId(id);
    }

    /**
     * Lister toutes les résas à venir d'un passager
     * @param id
     * @return
     */
    public List<ReservationVehicule> listerMesResasAVenir(Integer id) {
        return this.repository.findByPassagerIdAndDateHeureDepartGreaterThanEqual(id, LocalDate.now().atStartOfDay());
    }

    /**
     * Lister toutes les résas passées d'un passager
     * @param id
     * @return
     */
    public List<ReservationVehicule> listerMesResasHisto(Integer id) {
        return this.repository.findByPassagerIdAndDateHeureDepartLessThan(id, LocalDate.now().atStartOfDay());
    }

    /**
     * Annuler la réservation d'un passager
     * @param id
     * @return
     */
    public ReservationVehicule annuler(Integer id){
        return this.annulerId("Annulation à l'initiative du passager",id);
    }


    /**
     * Annulation résa : param motif et NotFound
     * @param motif
     * @param id
     * @return
     * @throws NotFoundException
     * @throws DateDepasseeException
     */
    public ReservationVehicule annulerId(String motif, Integer id) throws NotFoundException, DateDepasseeException {
        ReservationVehicule r = this.repository.findById(id)
                .orElseThrow(NotFoundException::new);
        return this.annulerResa(motif, r);
    }

    /**
     * Annulation résa : DateDepassee, DeleteByID et envoie email
     * @param motif
     * @param r
     * @return
     */
    private ReservationVehicule annulerResa(String motif, ReservationVehicule r) {
        if(r.getDateHeureDepart()
                .isBefore(LocalDateTime.now())){
            throw new DateDepasseeException();
        }
        this.repository.deleteById(r.getId());
        this.email.envoyerEmail(motif,r);
        return r;
    }


    /**
     * Insère une nouvelle resa en base à partir d'un dto
     * @param dto
     * @return l'objet résa inséré
     */
    public ReservationVehicule reserver(ReservationVehiculeDto dto) {
        ReservationVehicule r = new ReservationVehicule();
        r.setVehicule(this.vRepo.findById(dto.getVehicule())
                .orElseThrow(NotFoundException::new));
        r.setPassager(this.collaborateurRepository.findById(dto.getPassager())
                .orElseThrow(NotFoundException::new));
        r.setDateHeureDepart(dto.getDateHeureDepart());
        r.setDateHeureRetour(dto.getDateHeureRetour());
        if(dto.getChauffeur() != null){
            r.setChauffeur(this.chauffeurRepository.findById(dto.getChauffeur())
                    .orElseThrow(NotFoundException::new));
        }
        return this.repository.save(r);
    }


    /**
     * Ecrase une résa en base
     * @param resa
     * @return resa
     */
    public ReservationVehicule modifier(ReservationVehicule resa){
        if(this.repository.findById(resa.getId())
                .orElseThrow(NotFoundException::new)
                .getDateHeureDepart()
                .isBefore(LocalDateTime.now())){
            throw new DateDepasseeException();
        }
        return this.repository.save(resa);
    }

    /**
     * Renvoie la liste des résas concernant un chauffeur
     * @param id
     * @return
     */
    public List<ReservationVehicule> listerChauffeur(Integer id) {
        return this.repository.findByChauffeurId(id);
    }

    /**
     * Renvoie la liste des résas concernant un véhicule de service
     * @param id
     * @return
     */
    public List<ReservationVehicule> listerVehicule(Integer id) {
        return this.repository.findByVehiculeId(id);
    }

    /**
     * Accepte et retourne une réservation de véhicule de service par un chauffeur
     * @param dto
     * @return
     */
    @Transactional
    public ReservationVehicule accepter(AccepterMissionDto dto) {
        ReservationVehicule r = this.repository.findById(dto.getResa())
                .orElseThrow(NotFoundException::new);

        Chauffeur c = this.chauffeurRepository.findById(dto.getChauffeur())
                .orElseThrow(NotFoundException::new);

        r.setChauffeur(c);
        return r;
    }
}
