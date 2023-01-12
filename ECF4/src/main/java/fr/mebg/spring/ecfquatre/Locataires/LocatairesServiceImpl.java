package fr.mebg.spring.ecfquatre.Locataires;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class LocatairesServiceImpl implements LocatairesService {
    private final LocatairesRepository locatairesRepository;
    //pour avoir les log en console - tracking forensic des evenements de l'api.
    private static final Logger logger = LoggerFactory.getLogger(LocatairesServiceImpl.class);

    public LocatairesServiceImpl(LocatairesRepository locatairesRepository) {
        this.locatairesRepository = locatairesRepository;
    }

    /**
     * Fonction FINALL(). Cette fonction permet de recuperer tous les locataires en bases de données.
     *
     * @return une liste de locataire.
     */
    @Override
    public List<Locataire> findAll() {
        return locatairesRepository.findAll();
    }

    /**
     * Fonction Save() permet d'enregistrer un nouveau locataire en base de données.
     * Elle envoie une notification via le logger à chaque création.
     *
     * @param entity de type Locataire
     * @return l'entité qui vient d'être postée en base de donnée.
     */
    @Override
    public Locataire save(Locataire entity) {
        entity.setDateModification(LocalDateTime.now());
        logger.info("Creation en BDD de l'entite : " + entity.getId());
        return locatairesRepository.save(entity);
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
    @Override
    public Locataire findById(String id) {
        return locatairesRepository.findById(id).orElseThrow(() -> {
            logger.warn("tentative de recuperation d'une entite LOCATAIRE avec un id erroné");
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Fonction deleteById permet de supprimer un utilisateur en base de données.
     * La suppresion étant peut courrantes / annonymisation on reçoit un logger de type warn à chaque deletion
     *
     * @param id
     */
    @Override
    public void deleteById(String id) {
        logger.warn("deletion en base de donnée du locataire :" + id);
        locatairesRepository.deleteById(id);
    }

    /**
     * Fonction put permet la mise à jours d'un locataire.
     * L'id du path est recupéré, une fois la concordance vérifiée on regarde si l'entité existe en BDD, si oui, on la remplace par la nouvelle entité.
     *
     * @param id     du path, venant du useParams de REACT
     * @param entity utilisateur modifié
     * @return l'utilisateur qui a été modifié
     */
    @Override
    public Locataire miseAjourLocataire(String id, Locataire entity) {
        Locataire locataireAmodifier = this.findById(id);
        if (locataireAmodifier == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            locataireAmodifier.setDateModification(LocalDateTime.now());
            locataireAmodifier.setNom(entity.getNom());
            locataireAmodifier.setEmail(entity.getEmail());
            locataireAmodifier.setPrenom(entity.getPrenom());
            locataireAmodifier.setTelephone(entity.getTelephone());
            locataireAmodifier.setDateDeNaissance(entity.getDateDeNaissance());
        }
        return this.save(locataireAmodifier);
    }

}
