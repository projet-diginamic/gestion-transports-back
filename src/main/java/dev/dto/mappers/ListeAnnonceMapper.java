package dev.dto.mappers;

import dev.dto.AnnonceCovoiturageDetailDto;
import dev.entites.AnnonceCovoiturage;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = AnnonceCovoiturageMapper.class, componentModel = "spring")
public interface ListeAnnonceMapper {

    List<AnnonceCovoiturageDetailDto> map(List<AnnonceCovoiturage> l);
}
