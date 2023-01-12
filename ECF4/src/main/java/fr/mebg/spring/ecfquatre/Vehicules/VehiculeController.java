package fr.mebg.spring.ecfquatre.Vehicules;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/vehicules")
@CrossOrigin
public class VehiculeController {
    private final VehiculeService vehiculeService;

    public VehiculeController(VehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    /**
     * Fonction find All permet de recuperer tous les vehicules en base de donnée.
     *
     * @return une liste de vehicules.
     */
    @GetMapping("")
    public List<Vehicule> findAll() {
        return vehiculeService.findAll();
    }

    /**
     * Fonction save permet d'enregistrer un nouveau vehicule en base de donnée,
     * un logger notifie de la creation de le vehicule de type info en donnant le type et l'id.
     *
     * @param entity de type vehicule
     * @return le nouveau vehicule sauvegardé en base de données.
     */
    @PostMapping("")
    public Vehicule save(@RequestBody Vehicule entity) {
        return vehiculeService.save(entity);
    }

    /**
     * FindById est une fonction qui permet de recuperer une vehicule en BDD grâce à son id;
     * Si l'id utilisé est erroné ou non présent en BDD un logger de type warn est envoyé car cela peut être lié à
     * une attaque au vu du front qui est uniquement avec des select.
     * Une reponse Http est aussi renvoyée si l(id n'existe pas en BDD.
     *
     * @param id venant du path correspondant à l'element à rechercher en BDD.
     * @return le vehicule trouvée en BDD
     */
    @GetMapping("{id}")
    public Vehicule findById(@PathVariable String id) {
        return vehiculeService.findById(id);
    }

    /**
     * Fonction deleteById permet de supprimer un vehicule en base de données.
     * La suppresion étant peut courrantes on reçoit un logger de type warn à chaque deletion du vehicule
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        vehiculeService.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'un vehicule
     * L'id du path est recupéré,
     * une fois la concordance vérifiée on regarde si l'entité existe en BDD,
     * si oui, on la remplace par la nouvelle entité en mettant a jours celle en BDD.
     * @param id du path, venant du useParams de REACT
     * @param entity vehicule à modifier
     * @return le vehicule qui à été modifiée
     */
    @PutMapping("{id}")
    public Vehicule modificationVehiculeById(@PathVariable String id,
                                            @RequestBody Vehicule entity) {
        if (!Objects.equals(id, entity.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "id de l'objet et du path non concordants");
        } else {
            return this.vehiculeService.modificationVehiculeById(id, entity);
        }
    }

    /**
     * Fonction de modification de l'etat du vehicule en base de donnée.
     * @param id de le vehicule recuperer en base de donnée
     * @param etat nouvel etat de le vehicule
     * @return la vehicule modifiée
     */
    @PostMapping("{id}/etatModification")
    public Vehicule modificationEtatVehicule(@PathVariable String id,
                                            @RequestParam String etat){
        return this.vehiculeService.modificationEtatVehicule(id, etat);
    }
//    @GetMapping("/etat")
//    public List<Vehicule> recupererVoitureFonctionsEtat(@RequestParam String etat){
//        return this.vehiculeService.recupererVoitureFonctionsEtat(etat);
//    }
///************************MARCHE PAS***************************************************
    /**
     * ajoutDeLocationDansFicheVoiture est une fonction qui permet
     * l'Ajout de locations dans la liste de location du document Vehicule
     *
     * @param id
     * @param idl
     * @return
     */
//    @PostMapping("{id}/locataires")
//    public Boolean ajoutDeLocationDansDocumentVoiture(@PathVariable String id,
//                                                      @RequestParam String idl) {
//       return this.vehiculeService.ajoutDeLocationDansDocumentVoiture(id, idl);
//
//    }

}
