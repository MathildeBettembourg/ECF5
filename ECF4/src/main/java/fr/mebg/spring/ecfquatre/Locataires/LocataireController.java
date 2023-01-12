package fr.mebg.spring.ecfquatre.Locataires;

import fr.mebg.spring.ecfquatre.Locations.Location;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/locataires")
@CrossOrigin
public class LocataireController {
    private final LocatairesService locatairesService;


    public LocataireController(LocatairesService locatairesService) {
        this.locatairesService = locatairesService;
    }

    /**
     * Fonction FINALL(). Cette fonction permet de recuperer tous les locataires en bases de données.
     *
     * @return une liste de locataire.
     */
    @GetMapping("")
    public List<Locataire> findAll() {
        return locatairesService.findAll();
    }

    /**
     * Fonction Save() permet d'enregistrer un nouveau locataire en base de données.
     * Elle envoie une notification via le logger à chaque création.
     *
     * @param entity de type Locataire
     * @return l'entité qui vient d'être postée en base de donnée.
     */
    @PostMapping("")
    public Locataire save(@RequestBody Locataire entity) {
        return locatairesService.save(entity);
    }

    /**
     * Fonction findById est une fonction permet de rechercher et retourner un locatire qui est en base de donnée.
     * Si l'id utilisé est erroné ou non présent en BDD un logger de type warn est envoyé car cela peut être lié à
     * une attaque au vu du front qui est uniquement avec des select.
     * Une reponse Http est aussi renvoyée si l(id n'existe pas en BDD.
     *
     * @param id qui est l'Id de l'entité que l'on souhaite récuperer.
     * @return le Locataire trouvé en base de donnée
     */
    @GetMapping("{id}")
    public Locataire findById(@PathVariable String id) {
        return locatairesService.findById(id);
    }

    /**
     * Fonction deleteById permet de supprimer un utilisateur en base de données.
     * La suppresion étant peut courrantes / annonymisation on reçoit un logger de type warn à chaque deletion
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        locatairesService.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'un locataire.
     * L'id du path est recupéré, une fois la concordance vérifiée on regarde si l'entité existe en BDD, si oui, on la remplace par la nouvelle entité.
     *
     * @param id     du path, venant du useParams de REACT
     * @param entity utilisateur modifié
     * @return l'utilisateur qui a été modifié
     */
    @PutMapping("{id}")
    public Locataire miseAjourLocataire(@PathVariable String id,
                                        @RequestBody Locataire entity) {
        if (!Objects.equals(id, entity.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "id de l'objet et du path non concordants");
        } else {
            return this.locatairesService.miseAjourLocataire(id, entity);
        }
    }

    ;

}
