package fr.mebg.spring.ecfquatre.Locations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/locations")
@CrossOrigin
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    /**
     * Fonction findAll() permet de renvoyer toutes les locations en base de donnée (BDD).
     *
     * @return une liste de locations.
     */
    @GetMapping("")
    public List<Location> findAll() {
        return locationService.findAll();
    }

    /**
     * Fonction save permet de sauver une nouvlle location en base de BDD;
     * un logger avertit de cet enregistrement
     *
     * @param entity de type location
     * @return la nouvelle location enregistrée dans la BDD;
     */
    @PostMapping("")
    public Location save(@RequestBody Location entity) {
        return locationService.save(entity);
    }

    /**
     * Fonction findById est une fonction permet de rechercher et retourner une location qui est en BDD.
     * Si l'id utilisé est erroné ou bien non présent en BDD, un logger de type "warn" est envoyé.
     * Une reponse Http NOT_FOUND avec un message est aussi renvoyée si l'id n'existe pas en BDD.
     *
     * @param id qui est l'Id de l'entité que l'on souhaite récuperer.
     * @return la location trouvée en BDD
     */
    @GetMapping("{id}")
    public Location findById(@PathVariable String id) {
        return locationService.findById(id);
    }

    /**
     * Fonction DeleteById() permet de supprimer une location - impossible depuis mon front.
     * s'i 'il y avait une version "Manager" de localib elle aurait cette fonctionnalitéé.
     *
     * @param id de la location à supprimer
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        locationService.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'une location.
     * L'id du path est recupéré,
     * Une fois la concordance vérifiée entre le path et l'objet on regarde si l'entité existe en BDD,
     * si oui, on la remplace par la nouvelle entité.
     *
     * @param id     du path, venant du useParams de REACT, id de la location à modifier
     * @param entity location modifiée
     * @return la location qui a été modifiée
     */
    @PutMapping("{id}")
    public Location miseAjourLocation(@PathVariable String id,
                                      @RequestBody Location entity) {
        if (!Objects.equals(id, entity.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "id de l'objet et du path non concordants");
        } else {
            return this.locationService.miseAjourLocation(id, entity);
        }
    }
@GetMapping("/date")
    public List<Location> recupLocationsFinies(@RequestParam LocalDate dateFin){
        return this.recupLocationsFinies(dateFin);
}
}
