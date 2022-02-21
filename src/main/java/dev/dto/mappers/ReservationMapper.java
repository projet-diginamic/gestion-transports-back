package dev.dto.mappers;

import dev.dto.ReservationCovoiturageSimpleDto;
import dev.entites.reservation.ReservationCovoiturage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReservationMapper {

    @Mapping(source="reservationCovoiturage.annonceCovoiturage.dateHeureDepart", target="dateHeureDepart")
    @Mapping(source="reservationCovoiturage.annonceCovoiturage.adresseDepart", target="adresseDepart")
    @Mapping(source="reservationCovoiturage.annonceCovoiturage.adresseArrivee", target="adresseArrivee")
    ReservationCovoiturageSimpleDto toReservationCovoiturageSimpleDto(ReservationCovoiturage reservationCovoiturage);
}
