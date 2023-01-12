package fr.mebg.spring.ecfquatre.Locations;

import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    List<Location> findAll();

    Location save(Location entity);

    Location findById(String id);

    void deleteById(String id);

    Location miseAjourLocation(String id, Location entity);
    List<Location> recupLocationsFinies(LocalDate dateFin);
}
