package fr.mebg.spring.ecfquatre.Vehicules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class VehiculeServiceImpl implements VehiculeService {
    private final VehiculeRepository vehiculeRepository;

    private static final Logger logger = LoggerFactory.getLogger(VehiculeServiceImpl.class);

    public VehiculeServiceImpl(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;

    }

    /**
     * Fonction find All permet de recuperer tous les vehicules en base de donnée.
     *
     * @return une liste de vehicule.
     */
    @Override
    public List<Vehicule> findAll() {
        return vehiculeRepository.findAll();
    }

    /**
     * Fonction save permet d'enregistrer une nouveau vehicule en base de donnée,
     * un logger notifie de la creation de vehicule de type info en donnant le type et l'id.
     *
     * @param entity de type vehicule
     * @return nouveau vehicule sauvegardé en base de données.
     */
    @Override
    public Vehicule save(Vehicule entity) {
        logger.info("Creation en base de donne de l'entite VOITURE : " + entity.getId());
        entity.setDateModification(LocalDateTime.now());
        return vehiculeRepository.save(entity);
    }

    /**
     * FindById est une fonction qui permet de recuperer un vehicule en BDD grâce à son id;
     * Si l'id utilisé est erroné ou non présent en BDD un logger de type warn est envoyé car cela peut être lié à
     * une attaque au vu du front qui est uniquement avec des select.
     * Une reponse Http est aussi renvoyée si l(id n'existe pas en BDD.
     *
     * @param id venant du path correspondant à l'element à rechercher en BDD.
     * @return vehicule trouvée en BDD
     */
    @Override
    public Vehicule findById(String id) {
        return vehiculeRepository.findById(id).orElseThrow(() -> {
            logger.warn("tentative de recuperation d'une entite VOITURE avec un id erroné");
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Fonction deleteById permet de supprimer un voiture en base de données.
     * La suppresion étant peut courrantes on reçoit un logger de type warn à chaque deletion de vehicule
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        logger.warn("deletion de la VOITURE " + id);
        vehiculeRepository.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'un vehicule
     * L'id du path est recupéré,
     * une fois la concordance vérifiée on regarde si l'entité existe en BDD,
     * si oui, on la remplace par la nouvelle entité en mettant a jours celle en BDD.
     * * @param id     du path, venant du useParams de REACT
     *
     * @param entity vehicule à modifier
     * @return le vehicule qui à été modifié
     */
    @Override
    public Vehicule modificationVehiculeById(String id, Vehicule entity) {
        Vehicule vehiculeAmodifier = this.findById(id);
        if (vehiculeAmodifier == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            vehiculeAmodifier.setEtat(entity.getEtat());
            vehiculeAmodifier.setPrix(entity.getPrix());
            vehiculeAmodifier.setModele(entity.getModele());
            vehiculeAmodifier.setMarque(entity.getMarque());
            vehiculeAmodifier.setImmatriculation(entity.getImmatriculation());
            vehiculeAmodifier.setDisponibilite(entity.getDisponibilite());
        }
        return this.save(vehiculeAmodifier);

    }

    /**
     * Fonction existe by id pour verifier si la vehicule existe. Utilisé pour vérifier le status du vehicule
     *
     * @param id
     * @return un boolean
     */
    public boolean existsById(String id) {
        return vehiculeRepository.existsById(id);
    }

    /**
     * Fonction pour modifier via l'url l'etat du vehicule
     *
     * @param id   du vehicule
     * @param etat a changer
     * @return le vehicul modifié
     */
    @Override
    public Vehicule modificationEtatVehicule(String id, String etat) {
        Vehicule vehiculeAModifier = this.findById(id);
        if (Objects.equals(vehiculeAModifier.getEtat(), etat)) {
            return vehiculeAModifier;
        }
        vehiculeAModifier.setEtat(etat);
        return this.save(vehiculeAModifier);
    }

    @Override
    public List<Vehicule> recupererVoitureFonctionsEtat(String etat) {
        List<Vehicule> listeVehiculeEtat = new ArrayList<>();

        return null;
    }
/*****************************************************************************************************************************************
 /**
 * Fonction pour créer une liste de location de la voiture, permet de gérer les voitures
 *
 * @param id  de la voiture
 * @param idl de la location
 * @return
 */
//    @Override
//    public Boolean ajoutDeLocationDansDocumentVoiture(String id, String idl) {
//        Vehicule voitureOuDoitAjouterLocation = this.findById(id);
//        //liste de location ou doit ajouter location
//        List<Location> listeLocationsDeVoiture = voitureOuDoitAjouterLocation.getLocataireActuelEtAVenir();
//        Location locationAAjouterDansLaListe = this.locationService.findById(idl);
//        boolean flag = false;
//        if (listeLocationsDeVoiture.size() == 0) {
//            listeLocationsDeVoiture.add(locationAAjouterDansLaListe);
//        } else {
//            for (Location locationDeListe : listeLocationsDeVoiture
//            ) {
//                if ((Objects.equals(locationDeListe.getId(), voitureOuDoitAjouterLocation.getId()))
//                        || (locationDeListe.getFullend().isAfter(locationAAjouterDansLaListe.getFullstart()))
//                        || ((locationAAjouterDansLaListe.getFullend().isAfter(locationDeListe.getFullstart())
//                ))) {
//                    flag = true;
//                }
//            }
//            if (!flag) {
//                listeLocationsDeVoiture.add(locationAAjouterDansLaListe);
//                return flag;
//            }
//        }
//        return flag;
//    }
}
