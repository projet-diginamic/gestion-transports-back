package dev.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import dev.exception.ListeVideException;
import dev.utils.Resa;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import dev.dto.reservation.vehicule.AccepterMissionDto;
import dev.dto.reservation.vehicule.ReservationVehiculeDto;
import dev.entites.Chauffeur;
import dev.entites.reservation.ReservationVehicule;
import dev.exception.DateDepasseeException;
import dev.exception.NotFoundException;
import dev.repositories.ChauffeurRepository;
import dev.repositories.CollaborateurRepository;
import dev.repositories.ReservationVehiculeRepository;
import dev.repositories.VehiculeServiceRepository;
import dev.utils.Email;

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

	public ReservationVehiculeService(ReservationVehiculeRepository repository,
			CollaborateurRepository collaborateurRepository, ChauffeurRepository chauffeurRepository,
			VehiculeServiceRepository vRepo, Email email) {
		this.repository = repository;
		this.collaborateurRepository = collaborateurRepository;
		this.chauffeurRepository = chauffeurRepository;
		this.vRepo = vRepo;
		this.email = email;
	}

	/**
	 * Renvoie la liste si non vide, lance une exception sinon
	 * @param l
	 * @return
	 * @throws ListeVideException
	 */
	private List<ReservationVehicule> safeReturnList(List<ReservationVehicule> l) throws ListeVideException {
		if(l.isEmpty()) throw new ListeVideException("Pas de réservation de véhicule de service à renvoyer.");
		return l;
	}

	/**
	 * Renvoie la liste paginé des resas véhicules
	 * 
	 * @param pr
	 * @return
	 */
	public List<ReservationVehicule> listerVehicules(PageRequest pr) throws ListeVideException {
		return this.safeReturnList(this.repository.findAll(pr).toList());
	}

	/**
	 * Renvoie une résa véhicule par id
	 * 
	 * @param id
	 * @return
	 * @throws NotFoundException
	 */
	public ReservationVehicule afficherResa(Integer id) throws NotFoundException {
		return this.repository.findById(id).orElseThrow(NotFoundException::new);
	}

	/**
	 * Lister toutes les résas d'un passager
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerMesResas(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByPassagerId(id));
	}

	/**
	 * Lister toutes les résas à venir d'un passager
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerMesResasAVenir(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByPassagerIdAndDateHeureDepartGreaterThanEqual(id, LocalDate.now().atStartOfDay()));
	}

	/**
	 * Lister toutes les résas passées d'un passager
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerMesResasHisto(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByPassagerIdAndDateHeureDepartLessThan(id, LocalDate.now().atStartOfDay()));
	}

	/**
	 * Liste toutes les résas ACTIVES d'un passager
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	public List<ReservationVehicule> listerMesResasActives(Integer id) throws ListeVideException{
		return this.safeReturnList(this.repository.findByPassagerIdAndStatutLike(id, Resa.ACTIF.getVal()));
	}

	/**
	 * Liste toutes les résas ACTIVES d'un passager
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	public List<ReservationVehicule> listerMesResasArchives(Integer id) throws ListeVideException{
		return this.safeReturnList(this.repository.findByPassagerIdAndStatutLike(id, Resa.ARCHIVE.getVal()));
	}

	/**
	 * Liste toutes les résas ACTIVES d'un passager
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	public List<ReservationVehicule> listerMesResasAnnulees(Integer id) throws ListeVideException{
		return this.safeReturnList(this.repository.findByPassagerIdAndStatutLike(id, Resa.ANNULE.getVal()));
	}

	/**
	 * Annuler la réservation d'un passager
	 * 
	 * @param id
	 * @return
	 */
	public ReservationVehicule annuler(Integer id) {
		return this.annulerId("Annulation à l'initiative du passager", id);
	}

	/**
	 * Annulation résa : param motif et NotFound
	 * 
	 * @param motif
	 * @param id
	 * @return
	 * @throws NotFoundException
	 * @throws DateDepasseeException
	 */
	public ReservationVehicule annulerId(String motif, Integer id) throws NotFoundException, DateDepasseeException {
		ReservationVehicule r = this.repository.findById(id).orElseThrow(NotFoundException::new);
		return this.annulerResa(motif, r);
	}

	/**
	 * Annulation résa : DateDepassee, DeleteByID et envoie email
	 * 
	 * @param motif
	 * @param r
	 * @return
	 */
	private ReservationVehicule annulerResa(String motif, ReservationVehicule r) {
		if (r.getDateHeureDepart().isBefore(LocalDateTime.now())) {
			throw new DateDepasseeException();
		}
		r.setStatut(Resa.ANNULE.getVal());
		this.repository.save(r);
		this.email.envoyerEmail(motif, r);
		return r;
	}

	/**
	 * Insère une nouvelle resa en base à partir d'un dto
	 * 
	 * @param dto
	 * @return l'objet résa inséré
	 */
	public ReservationVehicule reserver(ReservationVehiculeDto dto) {
		ReservationVehicule r = new ReservationVehicule();
		r.setVehicule(this.vRepo.findById(dto.getVehicule()).orElseThrow(NotFoundException::new));
		r.setPassager(this.collaborateurRepository.findById(dto.getPassager()).orElseThrow(NotFoundException::new));
		r.setDateHeureDepart(dto.getDateHeureDepart());
		r.setDateHeureRetour(dto.getDateHeureRetour());
		r.setDemandeChauffeur(dto.getDemandeChauffeur());
		r.setStatut(Resa.ACTIF.getVal());
		return this.repository.save(r);
	}

	/**
	 * Ecrase une résa en base
	 * 
	 * @param resa
	 * @return resa
	 */
	public ReservationVehicule modifier(ReservationVehicule resa) {
		if (this.repository.findById(resa.getId()).orElseThrow(NotFoundException::new).getDateHeureDepart()
				.isBefore(LocalDateTime.now())) {
			throw new DateDepasseeException();
		}
		return this.repository.save(resa);
	}

	/**
	 * Renvoie la liste des résas ACTIVES concernant un chauffeur
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerChauffeur(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByChauffeurIdAndStatutLike(id, Resa.ACTIF.getVal()));
	}

	/**
	 * Renvoie la liste des résas ARCHIVEES concernant un chauffeur
	 *
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerChauffeurArchive(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByChauffeurIdAndStatutLike(id, Resa.ARCHIVE.getVal()));
	}

	/**
	 * Renvoie la liste des résas ANNULEES concernant un chauffeur
	 *
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerChauffeurAnnule(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByChauffeurIdAndStatutLike(id, Resa.ANNULE.getVal()));
	}

	/**
	 * Renvoie la liste des résas ACTIF concernant un véhicule de service
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	public List<ReservationVehicule> listerVehiculeActive(Integer id) throws ListeVideException{
		return this.safeReturnList(this.repository.findByVehiculeIdAndStatutLike(id, Resa.ACTIF.getVal()));
	}

	/**
	 * Renvoie la liste des résas ANNULEES concernant un véhicule de service
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	public List<ReservationVehicule> listerVehiculeAnnule(Integer id) throws ListeVideException{
		return this.safeReturnList(this.repository.findByVehiculeIdAndStatutLike(id, Resa.ANNULE.getVal()));
	}

	/**
	 * Renvoie la liste des résas ARCHIVEES concernant un véhicule de service
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
	public List<ReservationVehicule> listerVehiculeArchive(Integer id) throws ListeVideException{
		return this.safeReturnList(this.repository.findByVehiculeIdAndStatutLike(id, Resa.ARCHIVE.getVal()));
	}

	/**
	 * Renvoie la liste des résas concernant un véhicule de service
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerVehicule(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByVehiculeId(id));
	}

	/**
	 * Renvoie la liste des résas concernant un véhicule de service antérieures à la
	 * date/heure du jour
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerVehiculeHisto(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByVehiculeIdAndDateHeureDepartLessThan(id, LocalDate.now().atStartOfDay()));
	}

	/**
	 * Renvoie la liste des résas concernant un véhicule de service postérieures à
	 * la date/heure du jour
	 * 
	 * @param id
	 * @return
	 */
	public List<ReservationVehicule> listerVehiculeAvenir(Integer id) throws ListeVideException {
		return this.safeReturnList(this.repository.findByVehiculeIdAndDateHeureDepartGreaterThanEqual(id, LocalDate.now().atStartOfDay()));
	}

	/**
	 * Accepte et retourne une réservation de véhicule de service par un chauffeur
	 * 
	 * @param dto
	 * @return
	 */
	@Transactional
	public ReservationVehicule accepter(AccepterMissionDto dto) {
		ReservationVehicule r = this.repository.findById(dto.getResa()).orElseThrow(NotFoundException::new);

		Chauffeur c = this.chauffeurRepository.findById(dto.getChauffeur()).orElseThrow(NotFoundException::new);

		r.setChauffeur(c);
		return r;
	}

	/**
	 * Renvoie la liste des résas véhicules avec chauffeur non encore attribuées à
	 * un chauffeur
	 * 
	 * @return
	 */
	public List<ReservationVehicule> enAttente() throws ListeVideException {
		return this.safeReturnList(this.repository.enAttente(LocalDateTime.now()));
	}

	/**
	 * Liste de toutes les résas
	 * 
	 * @return
	 */
	public List<ReservationVehicule> lister() throws ListeVideException {
		return this.safeReturnList(this.repository.findAll());
	}

	/**
	 * Renvoie toutes les annonces ARCHIVE ET ANNULE d'un collaborateur
	 * @param id
	 * @return
	 * @throws ListeVideException
	 */
    public List<ReservationVehicule> listerMesResasAA(Integer id) throws ListeVideException {
		return this.safeReturnList(
				this.repository.findByPassagerIdAndStatutNotLike(id, Resa.ACTIF.getVal()));
    }
}
