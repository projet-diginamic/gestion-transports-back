package dev.dto.mappers;

import dev.dto.reservation.covoiturage.ReservationCovoiturageDetailDto;
import dev.exception.entites.reservation.ReservationCovoiturage;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class ReservationDetailMapper {

    @Autowired
    UtilisateurMapper utilisateurMapper;

    public ReservationCovoiturageDetailDto toReservationCovoiturageDetailDto(ReservationCovoiturage resa){
        ReservationCovoiturageDetailDto dto = new ReservationCovoiturageDetailDto();
        dto.setId(resa.getId());
        dto.setDateHeureDepart(resa.getAnnonceCovoiturage().getDateHeureDepart());
        dto.setAdresseDepart(resa.getAnnonceCovoiturage().getAdresseDepart());
        dto.setAdresseArrivee(resa.getAnnonceCovoiturage().getAdresseArrivee());
        dto.setPassagers(this.utilisateurMapper.map(resa.getAnnonceCovoiturage().getResas()));
        return dto;
    }


}