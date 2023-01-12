package fr.mebg.spring.ecfquatre.Vehicules;

import org.springframework.data.mongodb.repository.MongoRepository;


import java.util.List;

public interface VehiculeRepository extends MongoRepository<Vehicule, String> {

    List<Vehicule>findByDisponibilite(Boolean disponibilite);
}
