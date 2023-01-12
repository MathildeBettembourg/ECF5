package fr.mebg.spring.ecfquatre.Locations;

import fr.mebg.spring.ecfquatre.Locataires.LocatairesService;
import fr.mebg.spring.ecfquatre.Vehicules.VehiculeService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocationConfigurations {
    @Bean
    LocationService locationService(LocationRepository locationRepository, VehiculeService vehiculeService, LocatairesService locatairesService) {
        return new LocationServiceImpl(locationRepository, vehiculeService, locatairesService);
    }
}
