package dev.services;

import dev.dto.TauxOccupation;
import dev.repositories.ReservationVehiculeRepository;
import dev.utils.Resa;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ChauffeurService {

    private ReservationVehiculeRepository repository;

    public ChauffeurService(ReservationVehiculeRepository repository) {
        this.repository = repository;
    }

    private List tail(List l){
        return l.subList(1, l.size());
    }

    public List<TauxOccupation> taux(Integer id, LocalDate d1, LocalDate d2) {
        List<TauxOccupation> liste = this.repository
                .findByChauffeurIdAndStatutNotLikeAndDateHeureDepartGreaterThanEqualAndDateHeureRetourLessThanEqual(
                        id,
                        Resa.ANNULE.getVal(),
                        d1.atStartOfDay(),
                        d2.atStartOfDay()
                ).stream()
                .map(TauxOccupation::new)
                .sorted()
                .collect(Collectors.toList());

        List<LocalDate> dates = liste.stream()
                .map(x -> x.getDate())
                .collect(Collectors.toList());

        return Stream.iterate(d1, d -> d.plusDays(1))
                .limit(ChronoUnit.DAYS.between(d1,d2))
                .map(TauxOccupation::new)
                .map(x -> dates.indexOf(x.getDate()) == -1 ? x : liste.get(dates.indexOf(x.getDate())))
                .collect(Collectors.toList());
    }
}
