package fr.mebg.spring.ecfquatre.Vehicules;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VehiculeService {
    List<Vehicule> findAll();

    Vehicule save(Vehicule entity);

    Vehicule findById(String id);

    void deleteById(String id);

    Vehicule modificationVehiculeById(String id, Vehicule entity);

    boolean existsById(String id);

    Vehicule modificationEtatVehicule(String id, String etat);

    List<Vehicule> recupererVoitureFonctionsEtat(@RequestParam String etat);
//    Boolean ajoutDeLocationDansDocumentVoiture(String id, String idl);
}
