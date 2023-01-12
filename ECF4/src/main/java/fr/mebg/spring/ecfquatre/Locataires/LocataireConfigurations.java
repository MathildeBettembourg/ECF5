package fr.mebg.spring.ecfquatre.Locataires;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LocataireConfigurations {
    @Bean
    LocatairesService locatairesService(LocatairesRepository locatairesRepository) {
        return new LocatairesServiceImpl(locatairesRepository);
    }
}
