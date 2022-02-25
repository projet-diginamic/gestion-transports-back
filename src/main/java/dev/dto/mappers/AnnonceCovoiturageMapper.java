package dev.dto.mappers;

import dev.dto.AnnonceCovoiturageDetailDto;
import dev.entites.AnnonceCovoiturage;
import org.mapstruct.Mapper;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AnnonceCovoiturageMapper {

    default AnnonceCovoiturageDetailDto toAnnonceCovoiturageDetailDto(AnnonceCovoiturage a){
        AnnonceCovoiturageDetailDto dto = new AnnonceCovoiturageDetailDto();
        dto.setDateHeureDepart(a.getDateHeureDepart());
        dto.setAdresseDepart(a.getAdresseDepart());
        dto.setAdresseArrivee(a.getAdresseArrivee());
        dto.setNbPlaces(a.getNbPlaces());
        dto.setOrganisateur(a.getOrganisateur());
        dto.setVehicule(a.getVehicule());
        dto.setPassagers(a.getResas()
                .stream()
                .map(x -> x.getPassager())
                .collect(Collectors.toList()));
        dto.setNbResas(dto.getPassagers().size());
        return dto;
    }
}
