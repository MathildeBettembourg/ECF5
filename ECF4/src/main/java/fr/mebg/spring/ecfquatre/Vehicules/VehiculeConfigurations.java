package fr.mebg.spring.ecfquatre.Vehicules;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehiculeConfigurations {
    @Bean
    VehiculeService vehiculeService(VehiculeRepository vehiculeRepository){
        return new VehiculeServiceImpl(vehiculeRepository);
    }
}
