package dev.dto.mappers;

import dev.dto.UtilisateurDto;
import dev.entites.reservation.ReservationCovoiturage;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel ="spring")
public interface UtilisateurMapper {

    default List<UtilisateurDto> map(List<ReservationCovoiturage> l){
        return l.stream().map(x -> {
            UtilisateurDto y = new UtilisateurDto();
            y.setNom(x.getPassager().getNom());
            y.setPrenom(x.getPassager().getPrenom());
            return y;
        })
                .collect(Collectors.toList());
    }
}
