package dev.dto.mappers;

import dev.dto.reservation.covoiturage.ReservationCovoiturageSimpleDto;
import dev.entites.reservation.ReservationCovoiturage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = ReservationMapper.class,
componentModel = "spring")
public interface ListeReservationMapper {

    List<ReservationCovoiturageSimpleDto> map(List<ReservationCovoiturage> resas);
}
