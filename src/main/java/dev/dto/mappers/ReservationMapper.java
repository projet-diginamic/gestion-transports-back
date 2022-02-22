package dev.dto.mappers;

import dev.dto.reservation.covoiturage.ReservationCovoiturageSimpleDto;
import dev.exception.entites.reservation.ReservationCovoiturage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {


    @Mapping(source="resa.annonceCovoiturage.dateHeureDepart", target="dateHeureDepart")
    @Mapping(source="resa.annonceCovoiturage.adresseDepart", target="adresseDepart")
    @Mapping(source="resa.annonceCovoiturage.adresseArrivee", target="adresseArrivee")
    ReservationCovoiturageSimpleDto toReservationCovoiturageSimpleDto(ReservationCovoiturage resa);


}
